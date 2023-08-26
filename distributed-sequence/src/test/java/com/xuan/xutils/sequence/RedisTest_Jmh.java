package com.xuan.xutils.sequence;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Created by xuan on 2018/5/9.
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(8)
@State(Scope.Benchmark)
public class RedisTest_Jmh extends BaseTest {

    private Sequence sequence;

    @Setup
    public void setup() {
        sequence = initRedisSequence("RedisTest_Jmh");
    }

    @Benchmark
    public void test() {
        sequence.nextValue();
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(RedisTest_Jmh.class.getSimpleName()).output(
            LOG_PATH + "RedisTest_Jmh.log").forks(4).build();
        new Runner(options).run();
    }
}
