#!/usr/bin/env bash

# command build default version image
# bash build-topics-management-image.sh

# command build custom version image, pass version as argument to script file, example 8.1.0
# bash build-topics-management-image.sh 8.1.0

# final image topics-management:<version> and topics-management:latest

version=${1:-0.4.0}

docker image build \
    --file Topics-Management.Dockerfile \
    --build-arg version=${version} \
    --tag topics-management:${version} \
    --tag topics-management:latest \
    .