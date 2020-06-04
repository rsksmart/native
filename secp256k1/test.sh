#!/bin/bash
echo 'Running Secp256k1 tests'
docker run -i --rm -v $(pwd):/workdir  multiarch/crossbuild ./test_linux_inside.sh
