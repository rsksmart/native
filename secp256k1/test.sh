#!/bin/bash
echo 'Running Secp256k1 tests'
./autogen.sh && \
export JAVA_HOME=$(pwd)/src/java/jniheaders && \
./configure --host=x86_64-linux-gnu --enable-experimental --enable-module_ecdh --enable-module-recovery --enable-jni && \
make check