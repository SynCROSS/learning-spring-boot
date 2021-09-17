#!/usr/bin/env bash

ABSPATH=$(readlink -f "$0") # 현재 파일 위치 반환
ABSDIR=$(dirname "$ABSPATH") # stop.sh 경로 찾기 => profile.sh을 찾기 위해, 부모 경로 반환

source "${ABSDIR}"/profile.sh # 유사 import, 적용 또는 사용(?)

REPOSITORY=/home/ec2-user/app/step3

echo "> Copy Build File"
echo "cp $REPOSITORY/zip/*.jar $REPOSITORY/"
cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> Deploy New Application"

JAR_NAME=$(find $REPOSITORY/ -maxdepth 1 -name "*-SNAPSHOT-*.jar" -and \! -name "*-plain.jar" -exec ls -tr {} + | tail -n 1)
echo "> JAR Name: $JAR_NAME"

echo "> Grant Execution Privilege To '$JAR_NAME' $"
chmod +x "$JAR_NAME"

IDLE_PROFILE=$(find_idle_profile)
echo "Execute $JAR_NAME With Profile '$IDLE_PROFILE'"
nohup java -jar \
  -Dspring.config.location=classpath:/application.properties,classpath:/application-"$IDLE_PROFILE".properties,/home/ec2-user/app/application-oauth.properties, /home/ec-user/app/application-real-db.properties \
  -Dspring.profiles.active="$IDLE_PROFILE" \
  "$JAR_NAME" > $REPOSITORY/nohup.out 2>&1 &