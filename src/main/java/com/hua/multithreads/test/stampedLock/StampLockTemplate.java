package com.hua.multithreads.test.stampedLock;

import java.util.concurrent.locks.StampedLock;

public class StampLockTemplate {

    private StampedLock sl = new StampedLock();

    public void read() {
        //先用乐观读
        long stamp = sl.tryOptimisticRead();

        //读数据到局部变量

        //验证stamp
        if (!sl.validate(stamp)) {
            //验证不通过、数据被改过
            //升级为悲观读锁
            stamp = sl.readLock();
            try {
                //重新读数据到局部变量
            }finally {
                //释放悲观读锁
                sl.unlockRead(stamp);
            }
        }
        //处理逻辑，结束
    }

    public void write() {
        long stamp = sl.writeLock();
        try {
            //写共享变量
        }finally {
            //释放写锁
            sl.unlockWrite(stamp);
        }
    }
}
