#!/bin/bash

export KUBECONFIG="$(k3d get-kubeconfig --name='k3s-default')"

helm install --name sample-redis --values ../helm/values.yaml stable/redis
helm install --name sample-rabbitmq --values ../helm/values.yaml stable/rabbitmq
helm install --name sample-mysql --values ../helm/values.yaml stable/mysql

echo "sleep 40s, please wait ... "
sleep 40s

helm install --name sample ../helm
