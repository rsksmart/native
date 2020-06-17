# RSKJ Native Libraries

In this project you'll find all the native libraries used in rskj.

## Native Libraries

- altbn128
- [secp256k1](secp256k1/README.md)

## Build

Requirements 
- Docker
```
./gradlew buildProject
```

_Note: for linux run with `sudo`_

## Build a Specific Library

### Secp256k1

You can build this library for Linux, Mac & Windows (3 binaries will be placed at main/resources folder). Notice this is an experimental feature because Mac and Windows binaries aren't reproducibles  

```
./gradlew buildSecp256k1Experimental
```

Also, you can build this library just for the current OS (one binary will be placed at main/resources)

Requirements
- Autoconf
- Libtool

```
./gradlew buildSecp256k1CurrentOs
```

_Note: for linux run with `sudo`_

## Tests
 
```bash
./gradlew test
```

## Disclaimer

Experimental features should only be used under your own risk.
