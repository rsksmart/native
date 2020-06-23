# RSKJ Native Libraries

In this project you'll find all the native libraries used in rskj.

## Native Libraries

- altbn128
- [secp256k1](secp256k1/README.md)

## Build

## Productive

Requirements 
- Docker

First build docker container

```bash
docker build -t native-libs .
```

Then run it

```bash
docker run --rm -v $(pwd)/build:/native/build native-libs
```

## Development

You can use the same productive build to develop, but it will only work for linux. There is an experimental build which also supports mac and windows.

```bash
./gradlew buildExperimental
```

## Build a Specific Library

### Secp256k1

Requirements
- Autoconf
- Libtool

```
./gradlew buildSecp256k1CurrentOs
```

Build for Linux, Mac & Windows. Notice this is an experimental feature because Mac and Windows binaries aren't reproducibles  

Requirements
- Docker

```
./gradlew buildSecp256k1Experimental
```

## Tests
 
```bash
./gradlew test
```

## Checksums

```
libbn128.so: 53e325e98124c607ace30436ecb01110dc21285965b6269e427fe329c77e4f4d
libsecp256k1.so: 412baa29795c9d50fb15de2521d737092171867a35a33846a2367409a8526b52
native-1.1.0.jar: 9b851367af4eafd6af648e1ecbb51f5f19590b7b4418c0396ccf5d4a16b2b6ff
go1.13.5.linux-amd64.tar.gz: 512103d7ad296467814a6e3f635631bd35574cab3369a97a323c9a585ccaa569

$ java -version
openjdk version "1.8.0_252"
OpenJDK Runtime Environment (build 1.8.0_252-8u252-b09-1~18.04-b09)
OpenJDK 64-Bit Server VM (build 25.252-b09, mixed mode)
```

## Disclaimer

Experimental features should only be used under your own risk.
