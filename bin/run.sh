#!/usr/bin/env bash

mvn package
export $(cat .env | xargs)
java -Dexternal.properties.file='chatbot.properties' -jar target/chatbot-1.0.0.jar