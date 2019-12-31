#!/bin/bash

docker-compose stop redis
docker-compose stop rabbitmq
docker-compose stop mysql
docker-compose rm -f redis
docker-compose rm -f rabbitmq
docker-compose rm -f mysql
