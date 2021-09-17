#!/usr/bin/env bash

ABSPATH=$(readlink -f "$0") # 현재 파일 위치 반환
ABSDIR=$(dirname "$ABSPATH") # stop.sh 경로 찾기 => profile.sh을 찾기 위해, 부모 경로 반환

source "${ABSDIR}"/profile.sh # 유사 import, 적용 또는 사용(?)

function switch_proxy() {
    IDLE_PORT=$(find_idle_port)

    echo "> Port 2 B Switched: $IDLE_PORT"
    echo "> Switch Port"
    echo "set \$service_url http://localhost:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service_url.inc

    echo "> Reload NGINX"
    sudo service nginx reload
}