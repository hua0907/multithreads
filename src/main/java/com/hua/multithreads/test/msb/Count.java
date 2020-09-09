package com.hua.multithreads.test.msb;

public class Count implements Runnable{

    private int a = 100;

    @Override
    public synchronized void run() {
        a--;
        System.out.println(Thread.currentThread().getName() + ", a=" + a);
    }

    public static void main(String[] args) {
        Count c = new Count();
        for (int i = 0; i < 100; i++) {
            new Thread(c, "c" + i).start();
        }
    }
}
