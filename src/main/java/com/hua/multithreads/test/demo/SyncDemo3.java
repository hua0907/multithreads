package com.hua.multithreads.test.demo;

/**
 * 可同时执行
 */
public class SyncDemo3 {

    private final Object syncObject = new Object();
    private final Object syncObject1 = new Object();
    private final Object syncObject2 = new Object();

    private void print(String msg) {
        for (int i = 0; i < 10; i++) {
            System.out.println("print ... " + msg);
            Thread.yield();
        }
    }

    private void printA() {
        synchronized (syncObject) {
            print("A");
        }
    }

    private void printB() {
        synchronized (syncObject1) {
            print("B");
        }
    }

    private void printC() {
        synchronized (syncObject2) {
            print("C");
        }
    }

    public static void main(String[] args) {
        SyncDemo3 demo = new SyncDemo3();
//        ExecutorService pool = Executors.newFixedThreadPool(3);
//        new Thread(demo::printA).start();
        new Thread(demo::printB).start();
        new Thread(demo::printC).start();
        demo.printA();
    }
}
