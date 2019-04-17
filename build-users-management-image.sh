#!/usr/bin/env bash

version=${1:-0.1.0}

cd ./users-management/


docker image build \
    --file Users-Management.Dockerfile \
    --build-arg version=${version} \
    --tag users-management:${version} \
    --tag users-management:latest \
    .

cd -