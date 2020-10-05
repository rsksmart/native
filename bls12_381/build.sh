#!/bin/bash

PATH="$HOME/.cargo/bin:$PATH"

# delete old build dir, if exists
rm -rf "target/" && \
rm -rf "/native/src/main/resources/co/rsk/bls12_381" || true && \
mkdir -p "/native/src/main/resources/co/rsk/bls12_381" && \

# clean & build bls12-381
cargo clean && \
cargo build --lib --features eip_2357_c_api --release && \

# move to resources
cp target/release/libeth_pairings.so "/native/src/main/resources/co/rsk/bls12_381"
