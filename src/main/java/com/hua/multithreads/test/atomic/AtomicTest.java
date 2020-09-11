package com.hua.multithreads.test.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicTest implements Runnable {

    private int i = 0;

    public int getI() {
        return i;
    }

    private synchronized void increment() {
        i++;
        i++;
    }

    @Override
    public void run() {
        while (true) {
            increment();
        }
    }


    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        AtomicTest test = new AtomicTest();
        pool.execute(test);
        while (true) {
            int i = test.getI();
            if (i % 2 != 0) {
                System.out.println(i);
                break;
            }
        }


    }
}
