package com.hua.multithreads.test.stampedLock;

import java.util.concurrent.locks.StampedLock;

public class Point {

    private int x, y;

    final StampedLock sl = new StampedLock();

    double distanceFromOrigin() {
        //乐观读
        long stamp = sl.tryOptimisticRead();
        int curx = x, cury = y;

        if (!sl.validate(stamp)) {
            //升级为悲观读
            stamp = sl.readLock();
            try {
                curx = x;
                cury = y;
            } finally {
                sl.unlockRead(stamp);
            }

        }
        return Math.sqrt(curx * curx + cury * cury);

    }
}
