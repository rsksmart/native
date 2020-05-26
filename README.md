# RSKJ Native Libraries

In this project you'll find all the native libraries used in rskj.

## Native Libraries

- altbn128
- [secp256k1](secp256k1/README.md)

## Build

In order to build you'll need to have installed
- Docker
- Gradle CLI

Then run
1. `buildSecp256k1` gradle task
2. `jar` gradle task

Output at `build/libs`

## Build a Specific Library

There are gradle tasks to build each library.
- buildSecp256k1
