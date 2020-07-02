package com.hua.multithreads.test.countDownLacth;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LockDemo {

    private final Executor pool = Executors.newFixedThreadPool(2);


    private void run() throws InterruptedException {

        int i = 0;
        while (i < 10) {
            LockCond lc = new LockCond(2);
            Runnable r1 = () -> {
                System.out.println("订单");
                lc.down();
            };

            Runnable r2 = () -> {
                System.out.println("物流单");
                lc.down();
            };

            pool.execute(r1);
            pool.execute(r2);

            lc.ifZero();

            System.out.println("对账");
            System.out.println("-----------------------");
            i++;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo lockDemo = new LockDemo();
        lockDemo.run();
    }

}
