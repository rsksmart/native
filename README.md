# RSKJ Native Libraries

In this project you'll find all the native libraries used in rskj.

## Native Libraries

- altbn128
- [secp256k1](secp256k1/README.md)

## Build

Run `buildNativeRepo` gradle task

## Workflow with a JNI

When you modify any native call (from java side) you'll need run some gradle tasks to reflect them on the binaries and on the project.

These are the following steps (same steps for adding a new native call)

1. Modify whatever native call you want to 
2. Regenerate headers
3. Update binaries
4. Run tests for that library

I.E

Let's say you want to add a parameter to this method of secp256k1 library
```java
private static native byte[][] secp256k1_ecdsa_recover(ByteBuffer byteBuff, long context, int recid, int compressed); // adding compressed flag as an int
```

Then you'll need to run this gradle tasks
1. `generateSecp256k1Headers`
2. `updateSecp256k1Binaries`

