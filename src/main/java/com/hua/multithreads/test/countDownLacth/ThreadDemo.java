package com.hua.multithreads.test.countDownLacth;

public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread th1 = new Thread(() -> System.out.println("订单数据"));
        Thread th2 = new Thread(() -> System.out.println("配送单数据"));

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        System.out.println("对账操作");

    }
}
