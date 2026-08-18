#!/usr/bin/env bash
set -euo pipefail

readonly PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
readonly PLATFORM="${DSP_E2E_PLATFORM:?DSP_E2E_PLATFORM is required}"
readonly CASES="${DSP_E2E_CASES:?DSP_E2E_CASES is required}"

read -r -a runtime_cases <<<"${CASES}"
for runtime_case in "${runtime_cases[@]}"; do
  IFS=: read -r minecraft_version test_mode extra <<<"${runtime_case}"
  if [[ -z "${minecraft_version}" || -z "${test_mode}" || -n "${extra:-}" ]]; then
    echo "Invalid DSP_E2E_CASES entry: ${runtime_case}" >&2
    exit 2
  fi

  if [[ -n "${GITHUB_ACTIONS:-}" ]]; then
    echo "::group::${PLATFORM} ${minecraft_version} ${test_mode}"
  fi

  DSP_E2E_PLATFORM="${PLATFORM}" \
  DSP_E2E_VERSION="${minecraft_version}" \
  DSP_E2E_MODE="${test_mode}" \
  "${PROJECT_DIR}/integration/mineflayer/run-server-e2e.sh"

  if [[ -n "${GITHUB_ACTIONS:-}" ]]; then
    echo "::endgroup::"
  fi
done
