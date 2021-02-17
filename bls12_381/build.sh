#!/bin/bash

JAVA_BLS_RESOURCES="/native/src/main/resources/co/rsk/bls12_381"
PATH="/osxcross/target/bin:$PATH"
PATH="$HOME/.cargo/bin:$PATH"


# delete old build dir, if exists
rm -rf "target/" && \
rm -rf $JAVA_BLS_RESOURCES && \
mkdir -p $JAVA_BLS_RESOURCES && \
mkdir $JAVA_BLS_RESOURCES/macos && \
mkdir $JAVA_BLS_RESOURCES/linux && \
mkdir $JAVA_BLS_RESOURCES/win && \

# clean & build bls12-381 (linux)
echo "BUILD BLS12-381 LINUX" && \
rustup target add x86_64-unknown-linux-gnu && \
rustup toolchain install stable-x86_64-unknown-linux-gnu && \
cargo clean && \
cargo build --lib --features eip_2357_c_api --release --target x86_64-unknown-linux-gnu && \
cargo test && \
cp target/x86_64-unknown-linux-gnu/release/libeth_pairings.so $JAVA_BLS_RESOURCES/linux &&\

# clean & build bls12-381 (windows 64 bits)
echo "BUILD BLS12-381 WINDOWS" && \
rustup target add x86_64-pc-windows-gnu && \
rustup toolchain install stable-x86_64-pc-windows-gnu && \
cargo clean && \
cargo build --lib --features eip_2357_c_api --release --target x86_64-pc-windows-gnu && \
cp target/x86_64-pc-windows-gnu/release/eth_pairings.dll $JAVA_BLS_RESOURCES/win

# clean & build bls12-381 (mac) - toolchain: osxcross
echo "BUILD BLS12-381 MAC" && \
rustup target add x86_64-apple-darwin && \
cargo clean && \
cargo build --lib --features eip_2357_c_api --release --target x86_64-apple-darwin && \
cp target/x86_64-apple-darwin/release/libeth_pairings.dylib $JAVA_BLS_RESOURCES/macos
