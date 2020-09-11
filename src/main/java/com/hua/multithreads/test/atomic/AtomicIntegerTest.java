package com.hua.multithreads.test.atomic;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest implements Runnable{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public Integer getValue() {
        return atomicInteger.get();
    }

    private void increment() {
        atomicInteger.getAndAdd(2);
    }


    @Override
    public void run() {
        while (true) {
            increment();
        }
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        }, 5000);
        AtomicIntegerTest test = new AtomicIntegerTest();
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(test);

        while (true) {
            Integer value = test.getValue();
            if (value % 2 != 0) {
                System.out.println(value);
                break;
            }
        }
    }
}
