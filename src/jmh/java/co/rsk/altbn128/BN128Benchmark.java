package co.rsk.altbn128;

import co.rsk.altbn128.cloudflare.BN128Addition;
import co.rsk.altbn128.cloudflare.BN128Multiplication;
import co.rsk.altbn128.cloudflare.BN128Pairing;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class BN128Benchmark {

    @Benchmark
    public void cloudflareAdd() {
        new BN128Addition().execute(BenchMarkData.ADDITION_INPUT);
    }

    @Benchmark
    public void cloudflareMul() {
        new BN128Multiplication().execute(BenchMarkData.MULTIPLICATION_INPUT);
    }

    @Benchmark
    public void cloudflarePairing() {
        new BN128Pairing().execute(BenchMarkData.PAIRING_INPUT);
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(BN128Benchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(options).run();
    }
}