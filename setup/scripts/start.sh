#!/bin/bash

dateTime=$(date +%Y%m%d%H%M%S)

logFile=/opt/tranzai/logs

if [ -e /opt/tranzai/log.file ]; then
  mv log.file $logFile/log.file."$dateTime"
fi

nohup java -jar tranzai.jar > log.file 2>&1 &
