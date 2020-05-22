echo 'Updating secp256k1'
echo '---------------------------------------'

cd ../secp256k1 && ./build.sh && cd ../update-scripts

echo 'Removing old secp256k1 binaries'
rm -rf ../src/main/resources/org/
mkdir -p ../src/main/resources/org/bitcoin/native/Linux/x86_64
mkdir -p ../src/main/resources/org/bitcoin/native/Mac/x86_64
mkdir -p ../src/main/resources/org/bitcoin/native/Windows/x86_64

echo 'Getting new binaires from secp256k1 project'
cp -r ../secp256k1/build/Linux ../src/main/resources/org/bitcoin/native
cp -r ../secp256k1/build/Mac ../src/main/resources/org/bitcoin/native
cp -r ../secp256k1/build/Windows ../src/main/resources/org/bitcoin/native

echo '---------------------------------------'
echo 'UPDATED SECP256K1'