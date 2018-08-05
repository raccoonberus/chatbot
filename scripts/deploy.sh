#!/usr/bin/env bash

PID_FILE=/var/run/chatbot.pid

#sshpass -p $DEPLOY_PASSWORD ssh $DEPLOY_USER@$DEPLOY_HOST

#echo "Open project dir ${DEPLOY_PATH}"
#cd $DEPLOY_PATH
cd /root/chatbot

git reset --hard HEAD
git pull

echo 'Files updated'

mvn clean package -DskipTests=true

kill $(ps aux | grep 'chatbot-' | awk '{print $2}') || true

echo 'Run new version'
export $(cat .env | xargs) && \
    java -Dexternal.properties.file='chatbot.properties' -jar target/chatbot-1.0.0.jar &> ./chatbot.log &
#echo $! > ${PID_FILE}
echo 'Success!!!'
