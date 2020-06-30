package com.hua.multithreads.test.lock;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    private int value;

    public void addOne() {
        ReentrantLock lock = new ReentrantLock();

        lock.lock();
        try {
            value++;
        }finally {
            lock.unlock();
        }
    }
}
