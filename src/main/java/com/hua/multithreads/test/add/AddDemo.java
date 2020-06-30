package com.hua.multithreads.test.add;

import java.util.concurrent.atomic.AtomicInteger;

public class AddDemo {
    private static int count = 0;

    private static AtomicInteger a = new AtomicInteger();
    private synchronized void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
//            a.getAndAdd(1);
            count++;
        }
    }

    private int get() {
        return count;
    }

    public static int calc() throws InterruptedException {
        final AddDemo test = new AddDemo(); // 创建两个线程，执行add()操作
        Thread th1 = new Thread(() -> {
            test.add10K();
        });
        Thread th2 = new Thread(() -> {
            test.add10K();
        }); // 启动两个线程
        th1.start();
        th2.start(); // 等待两个线程执行结束
        th1.join();
        th2.join();
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(calc());

        System.out.println(new AddDemo().get());
    }
}
