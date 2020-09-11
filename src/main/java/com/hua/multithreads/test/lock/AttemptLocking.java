package com.hua.multithreads.test.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();

    public void unTimed() {
        boolean captured = lock.tryLock();

        try {
            System.out.println("try lock(): " + captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

    public void timed() {
        boolean captured = false;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("try lock(2, TimeUnit.SECONDS): " + captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        AttemptLocking al = new AttemptLocking();
        al.unTimed();
        al.timed();

        new Thread() {
            {
                setDaemon(true);
            }

            @Override
            public void run() {
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();

//        Thread.yield();
        Thread.sleep(2000);
        al.unTimed();
        al.timed();

    }

}
