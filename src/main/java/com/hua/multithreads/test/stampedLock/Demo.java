package com.hua.multithreads.test.stampedLock;

import java.util.concurrent.locks.StampedLock;

public class Demo {
    private StampedLock sl = new StampedLock();

    public void readLock() {
        long stamp = sl.readLock();

        //加读锁和解读锁
        try {
            System.out.println("读锁");
        }finally {
            sl.unlockRead(stamp);
        }
    }

    public void writerLock() {
        long stamp = sl.writeLock();

        try {
            System.out.println("写锁");
        }finally {
            sl.unlockWrite(stamp);
        }
    }

}
