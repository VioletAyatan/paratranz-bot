#!/bin/bash
##echo "kill process xljc-0.0.1-SNAPSHOT.jar"
PID=$(ps -ef | grep tranzai.jar | grep -v grep | awk '{print $2}')
if [ -z $PID ]; then
  echo "process server.jar not exist"
  exit
else
  echo "process id: $PID"
  kill -9 ${PID}
  echo "process server.jar killed"
fi
