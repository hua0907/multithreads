package com.hua.multithreads.test.countDownLacth;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockCond {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition zero = lock.newCondition();

    private int count;

    public LockCond(int count) {
        this.count = count;
    }

    public void down() {
        lock.lock();
        try {
            count--;
            if (count == 0) {
                zero.signal();
            }
        }finally {
            lock.unlock();
        }
    }

    public void ifZero() throws InterruptedException {
        lock.lock();
        try {
            while (0 != count) {
                zero.await();
            }
        }finally {
            lock.unlock();
        }

    }

}
