package com.hua.multithreads.test.demo;

/**
 * 不可同时执行
 */
public class SyncDemo2 {

    private final Object syncObject = new Object();

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
        synchronized (syncObject) {
            print("B");
        }
    }

    private void printC() {
        synchronized (syncObject) {
            print("C");
        }
    }

    public static void main(String[] args) {
        SyncDemo2 demo = new SyncDemo2();
        new Thread(demo::printB).start();
        new Thread(demo::printC).start();
        demo.printA();
    }
}
