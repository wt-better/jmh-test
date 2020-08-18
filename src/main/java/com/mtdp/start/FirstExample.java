package com.mtdp.start;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date created at 2019/7/24
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class FirstExample {

    /**
     * 定义四个参数，之后会分别对这四个参数进行测试
     */
    @Param({"1", "5", "10"})
    private int n;

    private List<Integer> arrayList = new ArrayList<>();
    private List<Integer> linkedList = new LinkedList<>();

    /**
     * 初始化方法，在全部Benchmark运行之前进行
     */
    @Setup(Level.Trial)
    public void init() {
        for (int i = 0; i < n; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
    }

    @Benchmark
    public void arrayTraverse() {
        for (int i = 0; i < n; i++) {
            arrayList.get(i);
        }
    }

    @Benchmark
    public void listTraverse() {
        for (int i = 0; i < n; i++) {
            linkedList.get(i);
        }
    }


    /**
     * 结束方法，在全部Benchmark运行之后进行
     */
    @TearDown(Level.Trial)
    public void arrayRemove() {
        for (int i = 0; i < n; i++) {
            arrayList.remove(0);
            linkedList.remove(0);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(FirstExample.class.getSimpleName())
                .forks(1)
                .warmupTime(new TimeValue(10, TimeUnit.MILLISECONDS))
                .measurementTime(new TimeValue(10, TimeUnit.MILLISECONDS))
                .warmupIterations(5)
                .measurementIterations(5)
                .build();

        new Runner(opt).run();
    }
}
