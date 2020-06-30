package com.hua.multithreads.test.add;

public class TestAddDemo {
    private static int count = 0;

    private void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            set(get() + 1);
        }
    }

    private synchronized int get() {
        return count;
    }

    private synchronized void set(int v) {
        count = v;
    }

    public static int calc() throws InterruptedException {
        final TestAddDemo test = new TestAddDemo(); // 创建两个线程，执行add()操作
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

    }
}
