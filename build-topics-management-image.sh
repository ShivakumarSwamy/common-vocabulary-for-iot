#!/usr/bin/env bash

version=${1:-0.4.0}

cd ./topics-management/


docker image build \
    --file Topics-Management.Dockerfile \
    --build-arg version=${version} \
    --tag topics-management:${version} \
    --tag topics-management:latest \
    .

cd -