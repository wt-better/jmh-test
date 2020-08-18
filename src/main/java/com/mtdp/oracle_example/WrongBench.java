package com.mtdp.oracle_example;

/**
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date created at 2019/7/24
 */
public class WrongBench {

    private static void nothing() {

    }

    private static double constant(double x1, double y1, double x2, double y2) {
        return 0.0d;
    }

    private static double distance(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    private static void bench(String name, long runMillis, int loop, int warmUp, int repeat, Runnable runnable) {
        System.out.println("Running: " + name);
        int max = repeat + warmUp;
        long average = 0L;
        for (int i = 0; i < max; i++) {
            long nOps = 0;
            long duration = 0L;
            long start = System.currentTimeMillis();
            while (duration < runMillis) {
                for (int j = 0; j < loop; j++) {
                    runnable.run();
                    nOps++;
                }
                duration = System.currentTimeMillis() - start;
            }
            long throughput = nOps / duration;
            boolean benchRun = i >= warmUp;
            if (benchRun) {
                average = average + throughput;
            }
            System.out.print(throughput + " ops/ms" + (!benchRun ? " (warmUp) | " : " | "));
        }
        average = average / repeat;
        System.out.println("\n[ ~" + average + " ops/ms ]\n");
    }

    private static final long RUN_MILLIS = 4000;
    private static final int REPEAT = 5;
    private static final int WARM_UP = 3;
    private static final int LOOP = 10_000;
    private static double last = 0.0d;

    public static void main(String... args) {
//        bench("nothing", RUN_MILLIS, LOOP, WARM_UP, REPEAT,
//                WrongBench::nothing);

//        38392604
        bench("distance", RUN_MILLIS, LOOP, WARM_UP, REPEAT, () ->
                distance(0.0d, 0.0d, 10.0d, 10.0d));

//        38403754
//        bench("constant", RUN_MILLIS, LOOP, WARM_UP, REPEAT, () ->
//                constant(0.0d, 0.0d, 10.0d, 10.0d));


//        31285401
//        bench("distance_use_return", RUN_MILLIS, LOOP, WARM_UP,
//                REPEAT, () -> last = distance(0.0, 0.0, 10.0, 10.0));
//        System.out.println(last);
    }
}