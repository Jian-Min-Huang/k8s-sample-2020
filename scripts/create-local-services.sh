#!/bin/bash

docker-compose up -d redis
docker-compose up -d rabbitmq
docker-compose up -d mysql
