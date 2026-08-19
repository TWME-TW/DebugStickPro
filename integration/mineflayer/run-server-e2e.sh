#!/usr/bin/env bash
set -euo pipefail

readonly PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
readonly PLATFORM="${DSP_E2E_PLATFORM:-paper}"
readonly MINECRAFT_VERSION="${DSP_E2E_VERSION:-1.19.4}"
readonly TEST_MODE="${DSP_E2E_MODE:-mineflayer}"
readonly MINEFLAYER_VERSION="${DSP_MINEFLAYER_VERSION:-${MINECRAFT_VERSION}}"
readonly JAVA_COMMAND="${DSP_E2E_JAVA:-java}"
readonly USERNAME="DSPTest"
readonly PORT="${DSP_E2E_PORT:-$(node -e 'const net=require("net");const server=net.createServer();server.listen(0,"127.0.0.1",()=>{console.log(server.address().port);server.close()})')}"
readonly TEMP_DIR="$(mktemp -d)"
readonly SERVER_DIR="${TEMP_DIR}/server"
readonly CACHE_DIR="${DSP_E2E_CACHE:-${HOME}/.cache/debugstickpro-e2e}"
readonly SERVER_JAR="${DSP_E2E_SERVER_JAR:-${CACHE_DIR}/${PLATFORM}-${MINECRAFT_VERSION}.jar}"
readonly PACKETEVENTS_JAR="${CACHE_DIR}/packetevents-spigot-2.13.0.jar"
readonly PACKETEVENTS_URL="https://github.com/retrooper/packetevents/releases/download/v2.13.0/packetevents-spigot-2.13.0.jar"
readonly PACKETEVENTS_SHA256="6d9ece0d87ee727a79a20b7ffbd432021609c6f52bafcb654fc2d3e9b6f064c5"
SERVER_PID=""

cleanup() {
  if [[ -n "${SERVER_PID}" ]] && kill -0 "${SERVER_PID}" 2>/dev/null; then
    kill "${SERVER_PID}" 2>/dev/null || true
    for _ in $(seq 1 10); do
      if ! kill -0 "${SERVER_PID}" 2>/dev/null; then break; fi
      sleep 1
    done
    if kill -0 "${SERVER_PID}" 2>/dev/null; then kill -KILL "${SERVER_PID}" 2>/dev/null || true; fi
    wait "${SERVER_PID}" 2>/dev/null || true
  fi
  rm -rf "${TEMP_DIR}"
}
trap cleanup EXIT

if [[ "${PLATFORM}" != "paper" && "${PLATFORM}" != "folia" && "${PLATFORM}" != "spigot" ]]; then
  echo "DSP_E2E_PLATFORM must be paper, folia, or spigot" >&2
  exit 2
fi
if [[ "${TEST_MODE}" != "mineflayer" && "${TEST_MODE}" != "startup" ]]; then
  echo "DSP_E2E_MODE must be mineflayer or startup" >&2
  exit 2
fi

mkdir -p "${CACHE_DIR}" "${SERVER_DIR}/plugins"
if [[ "${PLATFORM}" == "spigot" ]]; then
  if [[ ! -f "${SERVER_JAR}" ]]; then
    echo "Set DSP_E2E_SERVER_JAR to a Spigot jar built with BuildTools" >&2
    exit 2
  fi
else
  server_metadata="$(curl --fail --silent --show-error --location \
    "https://fill.papermc.io/v3/projects/${PLATFORM}/versions/${MINECRAFT_VERSION}/builds/latest")"
  server_url="$(jq -er '.downloads["server:default"].url' <<<"${server_metadata}")"
  server_sha256="$(jq -er '.downloads["server:default"].checksums.sha256' <<<"${server_metadata}")"
  if [[ ! -f "${SERVER_JAR}" ]] || ! echo "${server_sha256}  ${SERVER_JAR}" | sha256sum --check --status; then
    curl --fail --silent --show-error --location "${server_url}" --output "${SERVER_JAR}"
  fi
  echo "${server_sha256}  ${SERVER_JAR}" | sha256sum --check --status
fi

if [[ ! -f "${PACKETEVENTS_JAR}" ]] || ! echo "${PACKETEVENTS_SHA256}  ${PACKETEVENTS_JAR}" | sha256sum --check --status; then
  curl --fail --silent --show-error --location "${PACKETEVENTS_URL}" --output "${PACKETEVENTS_JAR}"
fi
echo "${PACKETEVENTS_SHA256}  ${PACKETEVENTS_JAR}" | sha256sum --check --status

find_plugin_jars() {
  find "${PROJECT_DIR}/target" -maxdepth 1 -type f \
    -name 'DebugStickPro-*.jar' -print 2>/dev/null | sort
}

mapfile -t plugin_jars < <(find_plugin_jars)
if (( ${#plugin_jars[@]} == 0 )); then
  mvn -B -f "${PROJECT_DIR}/pom.xml" package
  mapfile -t plugin_jars < <(find_plugin_jars)
fi
if (( ${#plugin_jars[@]} != 1 )); then
  echo "Expected exactly one DebugStickPro JAR in target, found ${#plugin_jars[@]}" >&2
  printf '  %s\n' "${plugin_jars[@]}" >&2
  exit 1
fi
readonly PLUGIN_JAR="${plugin_jars[0]}"

cp "${SERVER_JAR}" "${SERVER_DIR}/server.jar"
cp "${PACKETEVENTS_JAR}" "${SERVER_DIR}/plugins/packetevents.jar"
cp "${PLUGIN_JAR}" "${SERVER_DIR}/plugins/DebugStickPro.jar"

printf 'eula=true\n' >"${SERVER_DIR}/eula.txt"
printf '%s\n' \
  "server-port=${PORT}" \
  'online-mode=false' \
  'use-native-transport=false' \
  'spawn-protection=0' \
  'view-distance=4' \
  'simulation-distance=4' \
  'level-type=minecraft:flat' \
  'generate-structures=false' \
  'difficulty=peaceful' \
  'motd=DebugStickPro integration test' \
  >"${SERVER_DIR}/server.properties"

offline_uuid="$(node -e '
  const { createHash } = require("node:crypto")
  const bytes = createHash("md5").update(`OfflinePlayer:${process.argv[1]}`).digest()
  bytes[6] = (bytes[6] & 0x0f) | 0x30
  bytes[8] = (bytes[8] & 0x3f) | 0x80
  const hex = bytes.toString("hex")
  console.log(`${hex.slice(0,8)}-${hex.slice(8,12)}-${hex.slice(12,16)}-${hex.slice(16,20)}-${hex.slice(20)}`)
' "${USERNAME}")"
printf '[{"uuid":"%s","name":"%s","level":4,"bypassesPlayerLimit":false}]\n' \
  "${offline_uuid}" "${USERNAME}" >"${SERVER_DIR}/ops.json"

pushd "${SERVER_DIR}" >/dev/null
"${JAVA_COMMAND}" \
  --add-opens=java.base/java.nio=ALL-UNNAMED \
  --add-opens=java.base/sun.nio.ch=ALL-UNNAMED \
  -DPaper.IgnoreJavaVersion=true \
  -Dio.netty.tryReflectionSetAccessible=true \
  -Xms512M -Xmx1G -jar server.jar --nogui >server.log 2>&1 &
SERVER_PID="$!"
popd >/dev/null

for _ in $(seq 1 240); do
  if grep -q 'Done (' "${SERVER_DIR}/server.log" 2>/dev/null; then break; fi
  if ! kill -0 "${SERVER_PID}" 2>/dev/null; then
    tail -200 "${SERVER_DIR}/server.log" >&2
    exit 1
  fi
  sleep 1
done
if ! grep -q 'Done (' "${SERVER_DIR}/server.log"; then
  tail -200 "${SERVER_DIR}/server.log" >&2
  echo "${PLATFORM} ${MINECRAFT_VERSION} did not start within 240 seconds" >&2
  exit 1
fi
if ! grep -q 'Enabling DebugStickPro' "${SERVER_DIR}/server.log" || \
    grep -Eq 'Error occurred while enabling DebugStickPro|Could not load.*DebugStickPro|Could not load plugin.*DebugStickPro' "${SERVER_DIR}/server.log"; then
  tail -200 "${SERVER_DIR}/server.log" >&2
  echo "DebugStickPro did not enable cleanly" >&2
  exit 1
fi

if [[ "${TEST_MODE}" == "mineflayer" ]]; then
  (
    flock 9
    if ! node -e "require.resolve('mineflayer', { paths: ['${PROJECT_DIR}/integration/mineflayer'] })" >/dev/null 2>&1; then
      npm ci --prefix "${PROJECT_DIR}/integration/mineflayer" --silent
    fi
  ) 9>"${CACHE_DIR}/npm-install.lock"
  if ! DSP_E2E_PORT="${PORT}" DSP_MINEFLAYER_VERSION="${MINEFLAYER_VERSION}" \
    DSP_E2E_USERNAME="${USERNAME}" node "${PROJECT_DIR}/integration/mineflayer/test.mjs"; then
    tail -240 "${SERVER_DIR}/server.log" >&2
    exit 1
  fi
fi

if grep -Eq 'ThreadedRegionizer.*(ERROR|WARN)|not owned by current region|IllegalStateException:.*region' "${SERVER_DIR}/server.log"; then
  tail -200 "${SERVER_DIR}/server.log" >&2
  echo "Folia region-thread violation detected" >&2
  exit 1
fi

echo "${PLATFORM} ${MINECRAFT_VERSION} ${TEST_MODE} E2E passed"
