#!/bin/bash

export KUBECONFIG="$(k3d get-kubeconfig --name='k3s-default')"
helm install --name sample-mysql --set mysqlRootPassword="123456",mysqlDatabase=test stable/mysql
helm install --name sample ../helm
