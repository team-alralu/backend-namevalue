#!/bin/bash

echo "> now running app find!"
CURRENT_PID=$(pgrep -f namevalue)

echo "$CURRENT_PID"
if [ -z $CURRENT_PID ]; then
        echo "> no running app."
else
        echo "> kill -9 $CURRENT_PID"
        kill -9 $CURRENT_PID
        sleep 3
fi

echo "> new app deploy"

JAR_NAME=$(ls |grep 'namevalue' | tail -n 1)
echo "> JAR Name: $JAR_NAME"

echo "> run java"
nohup java -jar $JAR_NAME > /dev/null 2>&1 &