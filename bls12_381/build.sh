#!/bin/sh

PATH="$HOME/.cargo/bin:$PATH"

# delete old build dir, if exists
rm -rf "/native/bls12_381/src/main/resources/co.rsk.bls12_381" || true && \
mkdir -p "/native/bls12_381/src/main/resources/co.rsk.bls12_381" && \

# clean & build bls12-381
cargo clean && \
cargo build --lib --features eip_2357_c_api --release && \

# move to resources
rm -f "target/release/libeth_pairings.a" && \
rm -f "target/release/libeth_pairings.d" && \
rm -f "target/release/libeth_pairings.rlib" && \
cp target/release/libeth_pairings.* "/native/bls12_381/src/main/resources/co.rsk.bls12_381"