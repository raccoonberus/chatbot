#!/usr/bin/env bash

mvn clean package
docker-compose build --no-cache
docker-compose down
docker-compose up -d