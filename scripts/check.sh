#!/usr/bin/env bash

ABSPATH=$(readlink -f "$0") # 현재 파일 위치 반환
ABSDIR=$(dirname "$ABSPATH") # stop.sh 경로 찾기 => profile.sh을 찾기 위해, 부모 경로 반환

source "${ABSDIR}"/profile.sh # 유사 import, 적용 또는 사용(?)
source "${ABSDIR}"/switch.sh

IDLE_PORT=$(find_idle_profile_port)

echo "> Check If Spring Boot Was Executed Successfully"
echo "> curl -s http://localhost:$IDLE_PORT/profile"
sleep 10

for RETRY_COUNT in {1..10}
do
  RESPONSE=$(curl -s http://localhost:"${IDLE_PORT}"/profile)
  COUNT_OF_REAL=$(echo "${RESPONSE}" | grep -c 'real')

  if [ "${COUNT_OF_REAL}" -ge 1 ]
  then
    echo "> Spring Boot Was Executed Successfully"
    switch_proxy
    break
  else
    echo "> Spring Boot's Response Is Unknown Or Is Not Running"
    echo "> RESPONSE: ${RESPONSE}"
  fi

  if [ "${RETRY_COUNT}" -eq 10 ]
  then
      echo "> Check Failed 10 times"
      echo "> End The Deployment Without Connecting To Nginx"
      exit 1
  fi

  echo "> Check Failed. Retry Checking ..."
  sleep 10
done