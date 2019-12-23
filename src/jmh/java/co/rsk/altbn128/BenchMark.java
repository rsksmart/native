package co.rsk.altbn128;

import co.rsk.altbn128.cloudflare.BN128Addition;
import co.rsk.altbn128.cloudflare.BN128Multiplication;
import co.rsk.altbn128.cloudflare.BN128Pairing;
import org.openjdk.jmh.annotations.*;


public class BenchMark {

    @Fork(value = 1, warmups = 0)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void cloudflareAdd() {
        new BN128Addition().execute(BenchMarkData.ADDITION_INPUT);
    }

    @Fork(value = 1, warmups = 0)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void cloudflareMul() {
        new BN128Multiplication().execute(BenchMarkData.MULTIPLICATION_INPUT);
    }

    @Fork(value = 1, warmups = 0)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void cloudflarePairing() {
        new BN128Pairing().execute(BenchMarkData.PAIRING_INPUT);
    }

}