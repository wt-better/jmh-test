package com.mtdp.simaxiaochu;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * 死码消除
 * -XX:+PrintCompilation
 * Enables verbose diagnostic output from the JVM by printing a message to the console every time a method is compiled. This let’s you to see which methods actually get compiled. By default, this option is disabled and diagnostic output isn’t printed.
 * You can also log compilation activity to a file by using the -XX:+LogCompilation option.
 * output format:see https://ask.helplib.com/java/post_1142543
 *
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date created at 2019/7/24
 */
@BenchmarkMode(Mode.Throughput)
@State(Scope.Thread)
public class BeanMark {

    @Benchmark
    public void baseLine() {

    }

    @Benchmark
    public void measureWrong() {
        Math.log('x');
    }

    @Benchmark
    public double measure() {
        return Math.log('x');
    }

    @Benchmark
    public void measureRight(Blackhole bh) {
        bh.consume(Math.log('x'));
    }


    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(BeanMark.class.getSimpleName())
                .forks(1)
                //.warmupTime(new TimeValue(10, TimeUnit.MILLISECONDS))
                //.measurementTime(new TimeValue(10, TimeUnit.MILLISECONDS))
                .warmupIterations(5)
                .measurementIterations(5)
                .build();

        new Runner(options).run();
    }
}
