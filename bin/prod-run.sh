#!/usr/bin/env bash

docker rm chatbot --force
docker pull raccoonberus/chatbot_app:1.0.0
docker run -d --name chatbot --env-file ./.env -it raccoonberus/chatbot_app:1.0.0