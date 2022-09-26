#!/bin/bash

go get && make clean && make linux-amd64 && make linux-arm64
