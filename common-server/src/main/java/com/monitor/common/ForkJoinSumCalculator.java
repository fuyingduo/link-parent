package com.monitor.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RecursiveTask;

/**
 * created by fuyd on 2019-07-23
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private static final long THRESHOLD = 10;
    private static final long serialVersionUID = -7713852506702619738L;
    private static final Logger logger = LoggerFactory.getLogger(ForkJoinSumCalculator.class);
    private int start;
    private int end;
    private long[] numbers;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        logger.error(Thread.currentThread().getName());
        this.start = start;
        this.end = end;
        this.numbers = numbers;
    }

    @Override
    protected Long compute() {
        int length = start - end;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        long rightLong = rightTask.compute();
        long leftLong = leftTask.join();
        return rightLong + leftLong;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
