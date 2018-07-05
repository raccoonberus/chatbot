#!/usr/bin/env bash

mvn package
source .env
java -jar target/chatbot-1.0.0.jar