package com.switchvov.lambda.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinSumTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(forkJoinSum(1_000_000));
        long time = System.currentTimeMillis() - start;
        System.out.println("ForkJoin sum done in: " + time + " msecs" );
    }


    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
}
