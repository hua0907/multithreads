package com.hua.multithreads.test.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 不可同时执行
 */
public class LockDemo2 {
    private final Lock lock = new ReentrantLock();

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
        lock.lock();
        try {
            print("b");
        } finally {
            lock.unlock();
        }
    }

    private void printC() {
        lock.lock();
        try {
            print("c");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockDemo2 demo = new LockDemo2();
        new Thread(demo::printA).start();
        new Thread(demo::printB).start();
        demo.printC();
    }
}
