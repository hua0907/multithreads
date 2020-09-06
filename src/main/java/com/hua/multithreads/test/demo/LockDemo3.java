package com.hua.multithreads.test.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可同时执行
 */
public class LockDemo3 {
    private final Lock lock = new ReentrantLock();
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    private void print(String msg) {
        for (int i = 0; i < 10; i++) {
            System.out.println(msg);
            Thread.yield();
        }
    }

    private void printA() {
        lock.lock();
        try {
            print("a");
        } finally {
            lock.unlock();
        }
    }

    private void printB() {
        lock1.lock();
        try {
            print("b");
        } finally {
            lock1.unlock();
        }
    }

    private void printC() {
        lock2.lock();
        try {
            print("c");
        } finally {
            lock2.unlock();
        }
    }

    public static void main(String[] args) {
        LockDemo3 demo = new LockDemo3();
        new Thread(demo::printA).start();
        new Thread(demo::printB).start();
        demo.printC();
    }
}
