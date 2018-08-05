#!/usr/bin/env bash

PID_FILE=/var/run/chatbot.pid

#sshpass -p $DEPLOY_PASSWORD ssh $DEPLOY_USER@$DEPLOY_HOST

echo "Open project dir ${DEPLOY_PATH}"
#cd $DEPLOY_PATH
cd /root/chatbot

git reset --hard HEAD
git pull

echo 'Files updated'

if [[ -f ${PID_FILE} ]]; then
    kill $(ps aux | grep 'chatbot-' | awk '{print $2}')
#    kill -9 "$(cat ${PID_FILE})" || true
#    rm ${PID_FILE}
    echo 'Stop old version'
fi

mvn clean package -DskipTests=true
java -Dexternal.properties.file='chatbot.properties' -jar target/chatbot-1.0.0.jar &
echo $! > ${PID_FILE}


#IMAGE_NAME=chatbot_app
#DOCKER_ID_USER="raccoonberus"
#VERSION=$(mvn -Dexec.executable='echo' -Dexec.args='${project.version}' --non-recursive exec:exec -q 2> /dev/null)

#echo "Build: ${VERSION}"
#mvn clean package
#docker build -t $DOCKER_ID_USER/${IMAGE_NAME}:latest \
#    -t $DOCKER_ID_USER/${IMAGE_NAME}:${VERSION} .
#docker push $DOCKER_ID_USER/${IMAGE_NAME}:${VERSION}
#docker push $DOCKER_ID_USER/${IMAGE_NAME}:latest
#
#docker rm chatbot --force
#docker run -d --name chatbot --env-file ./.env -it raccoonberus/chatbot_app:1.0.0