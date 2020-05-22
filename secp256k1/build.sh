echo 'Building binaries'
echo '---------------------------------------------------------'

rm -rf build/ &&
mkdir -p build/Linux/x86_64 &&
mkdir -p build/Mac/x86_64 &&
mkdir -p build/Windows/x86_64

echo 'Building for linux'
./build_linux.sh &&
mv .libs/libsecp256k1.so.0.0.0 build/Linux/x86_64/libsecp256k1.so
echo '--------------------------'
echo 'Building for Mac'
./build_mac.sh &&
mv .libs/libsecp256k1.0.dylib build/Mac/x86_64/libsecp256k1.jnilib
echo '--------------------------'
echo 'Building for Windows'
./build_win.sh &&
mv .libs/libsecp256k1-0.dll build/Windows/x86_64/secp256k1.dll

echo '---------------------------------------------------------'
echo 'BUILDED BINARIES'