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
amd64/libbn128.so: 9de3f2efe3e0686734b79e41d8a0f8ea2c1e4f3e3d2c6ddcd10e3b60b5f973ca
arm64/libbn128.so: 9de3f2efe3e0686734b79e41d8a0f8ea2c1e4f3e3d2c6ddcd10e3b60b5f973ca
libsecp256k1.so: 0b99909b0c86b3f4bcad90f70eef81d7b5fd77f7d29997195b3291526e5cd7f0
native-1.2.1.jar: eb01e042c6c1ae38053add019b474971dae70c97a74846efc635416de55d77a1
go1.13.5.linux-amd64.tar.gz: 512103d7ad296467814a6e3f635631bd35574cab3369a97a323c9a585ccaa569

$ java -version
openjdk version "1.8.0_265"
OpenJDK Runtime Environment (build 1.8.0_265-8u265-b01-0ubuntu2~18.04-b01)
OpenJDK 64-Bit Server VM (build 25.265-b01, mixed mode)
```

## Disclaimer

Experimental/dev features should only be used under your own risk.
