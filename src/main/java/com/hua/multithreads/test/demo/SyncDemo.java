package com.hua.multithreads.test.demo;

/**
 * f()和g() 不同的锁 ，所以两个方法可同时执行
 */
public class SyncDemo {
    private final Object syncObject = new Object();

    private synchronized void f() {
        for (int i = 0; i < 5; i++) {
            System.out.println("f()");
            Thread.yield();
        }
    }

    private void g() {
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                System.out.println("g()");
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        SyncDemo syncDemo = new SyncDemo();
        new Thread(syncDemo::g).start();
        syncDemo.f();
    }
}
