package com.mtdp.stack_performance_test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date created at 2019/7/26
 */
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StackPerformanceTest {

    private GaStack fixGaStackStack = new ThreadUnsafeFixGaStack(1000000);
    private GaStack flexibleGaStack = new ThreadUnsafeGaStack();
    private Stack stack = new Stack();

//    @Benchmark
//    public void fixGAStack() {
//        fixGaStackStack.push('x');
//        fixGaStackStack.pop();
//    }

    @Param({"100000","1000000"})
    private int count;

    @Benchmark
    public void flexibleGAStack() {
        flexibleGaStack.push('x');
        //flexibleGaStack.pop();
    }

    @Benchmark
    public void jdkStack() {
        stack.push('x');
        //stack.pop();
    }


    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(StackPerformanceTest.class.getSimpleName()).forks(1)
                .warmupTime(new TimeValue(10, TimeUnit.MILLISECONDS))
                .measurementTime(new TimeValue(10, TimeUnit.MILLISECONDS))
                .warmupIterations(5)
                .measurementIterations(5)
                .build();

        new Runner(options).run();
    }
}
