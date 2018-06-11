#!/usr/bin/env bash

DOCKER_ID_USER="raccoonberus"

VERSION=$(mvn -Dexec.executable='echo' -Dexec.args='${project.version}' --non-recursive exec:exec -q 2> /dev/null)

mvn clean package
docker build -t $DOCKER_ID_USER/chatbot_app .
docker tag chatbot_app $DOCKER_ID_USER/chatbot_app:${VERSION}
docker push $DOCKER_ID_USER/chatbot_app:${VERSION}