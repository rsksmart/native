# RSKJ Native Libraries

In this project you'll find all the native libraries used in rskj.

## Native Libraries

- altbn128
- [secp256k1](secp256k1/README.md)

## Build

Currently productive build will only compile for linux systems (but every build command is mac compatilble).
Mac support for productive env will be added in future releases.

### Productive

```bash
> docker build -t native-libs .
> docker run --rm -v $(pwd)/build:/native/build native-libs
```

### Dev

Build every library and wrap them into native.jar

```bash
> ./gradlew buildProject
```

## Build a Specific Library

To build an specific library

```bash
# altbn128 (req: go)

> ./gradlew buildAltbn128

# secp256k1 (req: Autoconf, Libtool)

> ./gradlew buildSecp256k1

# bls12-381 (req: cargo)

> ./gradlew buildBls12_381
```

## Checksums

```
libbn128.so: 669543be939058001ffb5ca3b816d3961f39db82dad60dfd0ffc89540956dc6f
libsecp256k1.so: 0b99909b0c86b3f4bcad90f70eef81d7b5fd77f7d29997195b3291526e5cd7f0
libeth_pairings.so: 2a1d00f77ce59c4cede20a354cf523dd81a1bfc80a68ad0ea70322b420a88908
native-1.2.1.jar: b1357a0619596d496d01b095ceb45819337b0ee457b9df9cef4144ed560ea715
go1.13.5.linux-amd64.tar.gz: 512103d7ad296467814a6e3f635631bd35574cab3369a97a323c9a585ccaa569

$ java -version
openjdk version "1.8.0_265"
OpenJDK Runtime Environment (build 1.8.0_265-8u265-b01-0ubuntu2~18.04-b01)
OpenJDK 64-Bit Server VM (build 25.265-b01, mixed mode)
```

## Disclaimer

Experimental/dev features should only be used under your own risk.
