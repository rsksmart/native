package com.bakaoh.altbn128;

import org.openjdk.jmh.annotations.*;

public class BenchMark {

    @Fork(value = 1, warmups = 0)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void currentAdd() {
        new com.bakaoh.altbn128.current.BN128Addition().execute(BenchMarkData.ADDITION_INPUT);
    }

    @Fork(value = 1, warmups = 0)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void currentMul() {
        new com.bakaoh.altbn128.current.BN128Multiplication().execute(BenchMarkData.MULTIPLICATION_INPUT);
    }

    @Fork(value = 1, warmups = 0)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void currentPairing() {
        new com.bakaoh.altbn128.current.BN128Pairing().execute(BenchMarkData.PAIRING_INPUT);
    }

    @Fork(value = 1, warmups = 0)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void cloudflareAdd() {
        new com.bakaoh.altbn128.cloudflare.BN128Addition().execute(BenchMarkData.ADDITION_INPUT);
    }

    @Fork(value = 1, warmups = 0)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void cloudflareMul() {
        new com.bakaoh.altbn128.cloudflare.BN128Multiplication().execute(BenchMarkData.MULTIPLICATION_INPUT);
    }

    @Fork(value = 1, warmups = 0)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void cloudflarePairing() {
        new com.bakaoh.altbn128.cloudflare.BN128Pairing().execute(BenchMarkData.PAIRING_INPUT);
    }

}