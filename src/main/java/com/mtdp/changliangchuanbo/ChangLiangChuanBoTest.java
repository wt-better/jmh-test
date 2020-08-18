package com.mtdp.changliangchuanbo;

/**
 * 常量传播
 *
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date created at 2019/7/30
 */
public class ChangLiangChuanBoTest {

    private static void test1() {
        long sum = 0L;
        final int range = 8000;
        for (int i = 0; i < 10; i++) {
            long beginTime = System.nanoTime();
            for (int j = 0; j < 10000; j++) {
                for (int k = 0; k < range; k++) {
                    Integer integer = k;
                }
            }
            long endTime = System.nanoTime();
            sum = sum + (endTime - beginTime);
        }
        System.out.println(sum);
    }

    private static void test2() {
        long sum = 0L;
        for (int i = 0; i < 10; i++) {
            long beginTime = System.nanoTime();
            for (int j = 0; j < 10000; j++) {
                for (int k = 0; k < 8000; k++) {
                    Integer integer = k;
                }
            }
            long endTime = System.nanoTime();
            sum = sum + (endTime - beginTime);
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
//        536315750
        test1();
//        532248127
//        test2();
    }
}
