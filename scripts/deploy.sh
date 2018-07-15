#!/usr/bin/env bash

bash bin/build.sh

ssh ${PRODUCTION_USERNAME}@${PRODUCTION_SERVER}

cd chatbot/
git pull
docker pull raccoonberus/chatbot:1.0.0
bash bin/prod-run.sh