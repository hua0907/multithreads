package com.hua.multithreads.test.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockedQueue<T> {

    private List<T> list = new ArrayList<>(8);

    int num;

    private Lock lock = new ReentrantLock();

    private Condition notEmpty = lock.newCondition();

    private Condition notFull = lock.newCondition();

    void enq(T v) throws InterruptedException {
        lock.lock();
        try {
            int size = list.size();
            while (num >= size) {
                notFull.await();
            }

            list.add(v);
            num++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }


    void dequeue() throws InterruptedException {
        lock.lock();

        try {
            while (num == 0) {
                notEmpty.await();
            }
            num--;
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}
