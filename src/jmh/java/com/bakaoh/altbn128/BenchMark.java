package com.bakaoh.altbn128;

import com.bakaoh.altbn128.current.ContractFactory;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class BenchMark {

    public PrecompiledContract.Factory factory;

    @Setup(Level.Iteration)
    public void setUp() {
        factory = new ContractFactory();
    }

    @Fork(value = 1, warmups = 0)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void addition() {
        factory.newAddContract().execute(BenchMarkData.ADDITION_INPUT);
    }

    @Fork(value = 1, warmups = 0)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void multiplication() {
        factory.newMulContract().execute(BenchMarkData.MULTIPLICATION_INPUT);
    }

    @Fork(value = 1, warmups = 0)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void pairing() {
        factory.newPairingContract().execute(BenchMarkData.PAIRING_INPUT);
    }

}