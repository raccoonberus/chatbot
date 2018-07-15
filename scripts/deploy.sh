#!/usr/bin/env bash

cd ~/chatbot/
git pull
docker pull raccoonberus/chatbot:1.0.0
bash bin/prod-run.sh