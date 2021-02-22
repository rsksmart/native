# SECP256K1

This is a fork of the secp256k1 library, with a few modifications to JNI bindings (the rest of the code should not be modified).

Modifications are:
- implement missing "parse pubkey" method, which can be used to check that a public key is valid, and to decompress a public key
- add a new method that returns signature in compact format
- make verify handle both DER and compact signatures
- add a new method to add 2 public keys
- adapted `ecdsaRecover` to support compressed/uncompressed signatures

## JNI

JNI libraries for:
- Linux 64 bits

## Building

Requirements 
- Autoconf, Autotool & Libtool

```bash
./build.sh # Output at build/
```

## Testing
  
```bash
./test
```
    
## Disclaimer

This project it's only used for jni bindings, if you want to use secp256k1 library go to the [original repo](https://github.com/bitcoin-core/secp256k1)