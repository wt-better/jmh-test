package com.mtdp.simaxiaochu;

/**
 * 测试死码消除
 *
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date created at 2019/7/24
 */
public class SiMaXiaochu {

    private static final int REPEAT = 100000;
    private static double unBoxX = Math.PI;
    private static Double boxX = Math.PI;

    private static void baseLine() {

    }

    private static void measureWrong() {
        Math.log(unBoxX);
    }

    private static double measure() {
        return Math.log(unBoxX);
    }


    /**
     * 10000次
     * baseLine 428390ns
     * measureWrong 408555ns
     * measure 519337
     * <p>
     * 100000次
     * baseLine 2357280ns
     * measureWrong 2306901ns
     * measure 3355883ns
     */
    public static void main(String[] args) {
        long start = System.nanoTime();
        for (int i = 0; i < REPEAT; i++) {
            measureWrong();
        }
        long end = System.nanoTime();
        System.out.println(end - start);
    }
}
