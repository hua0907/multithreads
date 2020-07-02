package com.hua.multithreads.test.countDownLacth;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    private final Executor pool = Executors.newFixedThreadPool(2);


    public void run() throws InterruptedException {
        int i = 0;
        while (i < 10) {

            CountDownLatch c = new CountDownLatch(2);
            Runnable r1 = () -> {
                System.out.println("订单");
                c.countDown();
            };

            Runnable r2 = () -> {
                System.out.println("物流单");
                c.countDown();
            };

            pool.execute(r1);
            pool.execute(r2);

            c.await();//等待计数器变为0

            System.out.println("对账操作");
            System.out.println("--------------------");
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolDemo demo = new ThreadPoolDemo();
        demo.run();

    }

}
