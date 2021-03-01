# RSKJ Native Libraries

In this project you'll find all the native libraries used in rskj.

## Native Libraries

- altbn128
- [secp256k1](secp256k1/README.md)

### Compatibility

| Library       | Linux         | Mac OS    | Windows (x64)  |
| ------------- |:-------------:| :--------:| :-------:|
| altbn128      | x             |           |          |
| secp256k1     | x             |           |          |

## Build

Builds each library, runs all the tests (only for linux) and bundles `native-x.y.z.jar`

```bash
> docker build -t native-libs .
> docker run --rm -v $(pwd)/build:/native/build native-libs
```

## Tests

You'll only be able to run tests if your system it's compatible (linux recommended).

## Build a Specific Library

To build an specific library

```bash
# altbn128 (req: go)

> ./gradlew buildAltbn128

# secp256k1 (req: Autoconf, Libtool)

> ./gradlew buildSecp256k1
```

## Checksums

```bash
# altbn128
libbn128.so: ee41baa43b5a3927e99c2d0f826666e5baf2885ec0d689ea3c591db35ad9ae47

# secp256k1
libsecp256k1.so: dd55be8c14220f6c846a52ac891ff1d6f1f35d1f8bb5feb89364e420a50752a9

native-1.3.0.jar: 85249d0333ec4997a61726e9665d5a7df3285599f6dab20cbdf9eb94488ebce0
go1.13.5.linux-amd64.tar.gz: 512103d7ad296467814a6e3f635631bd35574cab3369a97a323c9a585ccaa569

$ java -version
openjdk version "1.8.0_275"
OpenJDK Runtime Environment (build 1.8.0_275-8u275-b01-0ubuntu1~18.04-b01)
OpenJDK 64-Bit Server VM (build 25.275-b01, mixed mode)
```
