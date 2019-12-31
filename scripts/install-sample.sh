#!/bin/bash

export KUBECONFIG="$(k3d get-kubeconfig --name='k3s-default')"
helm install --name sample-redis --values ../hlem/values.yaml stable/redis
helm install --name sample-rabbitmq --values ../hlem/values.yaml stable/rabbitmq
helm install --name sample-mysql --values ../hlem/values.yaml stable/mysql
#helm install --name sample ../helm
