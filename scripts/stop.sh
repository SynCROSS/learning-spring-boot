#!/usr/bin/env bash

ABSPATH=$(readlink -f -- "$0") # 현재 파일 위치 반환
ABSDIR=$(dirname "$ABSPATH") # stop.sh 경로 찾기 => profile.sh을 찾기 위해, 부모 경로 반환

source "${ABSDIR}"/profile.sh # 유사 import, 적용 또는 사용(?)

IDLE_PORT=$(find_idle_profile_port)

echo "> Check PID Of Application Running At $IDLE_PORT"
IDLE_PID=$(lsof -ti tcp:"${IDLE_PORT}") # 해당 포트에 열린 파일의 PID -t: PID만 표시 -i: 지정하지 않으면 모든 Port

if [ -z "${IDLE_PID}" ]
then
  echo "> No Applications Are Currently Running & Will NOT Shut Down"
else
    echo "> kill -15 $IDLE_PID"
    kill -15 "${IDLE_PID}"
    sleep 5
fi