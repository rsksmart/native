# RSKJ Native Libraries

In this project you'll find all the native libraries used in rskj.

## Native Libraries

- altbn128
- [secp256k1](secp256k1/README.md)

## Build

### Productive

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

### Dev

There is a second experimental build process. With this one you'll be able to do a cross platform build for secp256k1.  

```bash
./gradlew buildDev
```

NOTE: Linux, Mac & Win binaries at `resources/`

## Build a Specific Library

### Altbn128

Requirements
- go

```
./gradlew buildAltbn128
```

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
./gradlew buildSecp256k1Cross
```

## Tests
 
```bash
./gradlew test
```

## Checksums

```
amd64/libbn128.so: b934b7d2abb1cca4a6d4b828576c694732fdc68144ec35dab7fc4b28d1f83f16
arm64/libbn128.so: a1130c647bc0497737cea57b8de9f2cea1db2c3842e69732c736eec2d80d2f0e
libsecp256k1.so: 0b99909b0c86b3f4bcad90f70eef81d7b5fd77f7d29997195b3291526e5cd7f0
native-1.2.1.jar: 659ca25a7b020a91f11fc1a2973128e314198896ab99a447e608cb1d9a3ff5a3
go1.13.5.linux-amd64.tar.gz: 512103d7ad296467814a6e3f635631bd35574cab3369a97a323c9a585ccaa569

$ java -version
openjdk version "1.8.0_265"
OpenJDK Runtime Environment (build 1.8.0_265-8u265-b01-0ubuntu2~18.04-b01)
OpenJDK 64-Bit Server VM (build 25.265-b01, mixed mode)
```

## Disclaimer

Experimental/dev features should only be used under your own risk.
