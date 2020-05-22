NATIVE_LIBRARIES_DIRECTORY='../src/main/resources/org/bitcoin/native'
SECP256K1_BUILD_DIRECTORY='../secp256k1/build'
GENERATED_HEADERS_DIRECTORY='../build/generated/jni'

echo 'Bulding secp256k1'
echo '---------------------------------------'

echo 'Generating JNI Headers'
cd ../ &&
gradle generateJniHeaders &&
cd secp256k1 &&
cp "$GENERATED_HEADERS_DIRECTORY/org_bitcoin_NativeSecp256k1.h" "jni/" &&
cp "$GENERATED_HEADERS_DIRECTORY/org_bitcoin_Secp256k1Context.h" "jni/" &&
./build.sh &&
cd ../build-scripts

echo 'Removing old secp256k1 binaries'
rm -rf "$NATIVE_LIBRARIES_DIRECTORY"
mkdir -p "$NATIVE_LIBRARIES_DIRECTORY/Linux/x86_64"
mkdir -p "$NATIVE_LIBRARIES_DIRECTORY/Mac/x86_64"
mkdir -p "$NATIVE_LIBRARIES_DIRECTORY/Windows/x86_64"

echo 'Getting new binaires from secp256k1 project'
cp -r "$SECP256K1_BUILD_DIRECTORY/Linux" $NATIVE_LIBRARIES_DIRECTORY
cp -r "$SECP256K1_BUILD_DIRECTORY/Mac" $NATIVE_LIBRARIES_DIRECTORY
cp -r "$SECP256K1_BUILD_DIRECTORY/Windows" $NATIVE_LIBRARIES_DIRECTORY

echo '---------------------------------------'

cd ../secp256k1 &&
echo 'Running secp256k1 tests' &&
./test.sh &&
cd .. &&
echo 'Running secp256k1 JNI tests' &&
gradle test # First it will run library tests and then the JNI tests

echo '---------------------------------------'
echo 'BUILDED SECP256K1'