#!/bin/bash
dateTime=$(date +%Y%m%d%H%M%S)
logFile=/opt/server/logs
if [ -e /opt/server/log.out ]; then
  mv log.out $logFile/log.out.$dateTime
fi
nohup java -Xms500m -Xmx500m -Xmn250m -Xss256k -server -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar /opt/server/tranzai.jar >log.out &
