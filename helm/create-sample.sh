#!/bin/bash

export KUBECONFIG="$(k3d get-kubeconfig --name='k3s-default')"
helm install --name sample-mysql --set mysqlRootPassword=,mysqlDatabase=test stable/mysql
helm install --name sample .
