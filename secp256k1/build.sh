#!/bin/bash

# delete old build dir, if exists
rm -rf "/native/src/main/resources/org/bitcoin/native/Linux/x86_64/" && \
mkdir -p "/native/src/main/resources/org/bitcoin/native/Linux/x86_64/" && \

# run tests
./test.sh && \

# clean & build secp256k1
./autogen.sh && \
./configure --host=x86_64-linux-gnu --enable-experimental --enable-module_ecdh --enable-module-recovery --enable-jni && \
make clean && \
make && \

# move to resources
cp .libs/libsecp256k1.so.0.0.0 "/native/src/main/resources/org/bitcoin/native/Linux/x86_64/libsecp256k1.so"