#!/bin/bash

export KUBECONFIG="$(k3d get-kubeconfig --name='k3s-default')"
helm install --name sample-mysql --set mysqlRootPassword="1qaz2wsx",mysqlDatabase=test stable/mysql
helm install --name sample-rabbitmq --set rabbitmq.username=rabbitmq,rabbitmq.password=rabbitmq stable/rabbitmq
helm install --name sample ../helm
