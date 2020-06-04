# RSKJ Native Libraries

In this project you'll find all the native libraries used in rskj.

## Native Libraries

- altbn128
- [secp256k1](secp256k1/README.md)

## Build

Requirements 
- Docker
- Gradle CLI
- Autoconf, Autotool & Libtool

```
./gradlew buildProject
```

Output at `build/libs`

## Build a Specific Library

- Secp256k1: `./gradlew buildSecp256k1Cross`
