#!/bin/bash

# delete old build dir, if exists
rm -rf "/native/src/main/resources/co/rsk/altbn128/" || true && \
mkdir -p "/native/src/main/resources/co/rsk/altbn128/" && \

# go, clean & build
go get && \
make clean && \
make linux && \

# move to resources
mv libbn128.so "/native/src/main/resources/co/rsk/altbn128/"


