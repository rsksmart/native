#!/bin/bash

echo 'Building binaries'
echo '---------------------------------------------------------'

cd "$(dirname "$0")"

rm -rf build/ &&
mkdir -p build/Linux/x86_64 &&
mkdir -p build/Mac/x86_64 &&
mkdir -p build/Windows/x86_64

echo 'Building for linux'
./build_linux.sh &&
mv .libs/libsecp256k1.so.0.0.0 build/Linux/x86_64/
echo '--------------------------'
echo 'Building for Mac'
./build_mac.sh &&
mv .libs/libsecp256k1.0.dylib build/Mac/x86_64/
echo '--------------------------'
echo 'Building for Windows'
./build_win.sh &&
mv .libs/libsecp256k1-0.dll build/Windows/x86_64/

echo '---------------------------------------------------------'
echo 'BUILDED BINARIES'