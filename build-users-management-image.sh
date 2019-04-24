#!/usr/bin/env bash

# command build default version image
# bash build-users-management-image.sh

# command build custom version image, pass version as argument to script file, example 8.1.0
# bash build-users-management-image.sh 8.1.0

# final image users-management:<version> and users-management:latest

version=${1:-0.1.2}

docker image build \
    --file Users-Management.Dockerfile \
    --build-arg version=${version} \
    --tag users-management:${version} \
    --tag users-management:latest \
    .