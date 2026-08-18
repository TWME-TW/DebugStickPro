#!/usr/bin/env bash
set -euo pipefail

readonly PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
readonly MINECRAFT_VERSION="${DSP_E2E_VERSION:-1.19.4}"
readonly CACHE_DIR="${DSP_E2E_CACHE:-${HOME}/.cache/debugstickpro-e2e}"
readonly BUILD_DIR="${CACHE_DIR}/buildtools-${MINECRAFT_VERSION}"
readonly BUILD_TOOLS_JAR="${CACHE_DIR}/BuildTools.jar"
readonly SPIGOT_JAR="${CACHE_DIR}/spigot-${MINECRAFT_VERSION}.jar"
readonly BUILD_TOOLS_URL="https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar"
readonly BUILD_JAVA_COMMAND="${DSP_BUILDTOOLS_JAVA:-java}"

mkdir -p "${CACHE_DIR}" "${BUILD_DIR}"
if [[ ! -f "${BUILD_TOOLS_JAR}" ]]; then
  curl --fail --silent --show-error --location "${BUILD_TOOLS_URL}" --output "${BUILD_TOOLS_JAR}"
fi

if [[ ! -f "${SPIGOT_JAR}" ]]; then
  pushd "${BUILD_DIR}" >/dev/null
  "${BUILD_JAVA_COMMAND}" -jar "${BUILD_TOOLS_JAR}" --rev "${MINECRAFT_VERSION}" --compile SPIGOT
  popd >/dev/null
  built_jar="$(find "${BUILD_DIR}" -maxdepth 1 -type f -name "spigot-${MINECRAFT_VERSION}*.jar" -print -quit)"
  if [[ -z "${built_jar}" ]]; then
    echo "BuildTools did not produce Spigot ${MINECRAFT_VERSION}" >&2
    exit 1
  fi
  cp "${built_jar}" "${SPIGOT_JAR}"
fi

DSP_E2E_PLATFORM=spigot \
DSP_E2E_VERSION="${MINECRAFT_VERSION}" \
DSP_E2E_SERVER_JAR="${SPIGOT_JAR}" \
DSP_E2E_JAVA="${DSP_SPIGOT_JAVA:-${BUILD_JAVA_COMMAND}}" \
"${PROJECT_DIR}/integration/mineflayer/run-server-e2e.sh"
