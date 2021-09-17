#!/usr/bin/env bash

REPOSITORY=/home/ec2-user/app/step2

echo "> Copy Build File"
cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> Check PID of Running Application"

CURRENT_PID=$(pgrep -fl learning-spring | grep java | awk '{print $1}') # 같은 이름의 프로세스 중 jar 프로세스에서 선택
echo "PID of Running Application: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> No Applications Are Currently Running & Will NOT Shut Down"
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 "$CURRENT_PID"
  sleep 5
fi

echo "> Deploy New Application"

JAR_NAME=$(find $REPOSITORY/ -maxdepth 1 -name "*-SNAPSHOT.jar"  | tail -n 1)
echo "> JAR Name: $JAR_NAME"

echo "> Grant Execution Privilege To '$JAR_NAME' $"
chmod +x "$JAR_NAME"

echo "> Execute $JAR_NAME"
nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
    -Dspring.profiles.active=real \
    "$JAR_NAME" > $REPOSITORY/nohup.out 2>&1 &