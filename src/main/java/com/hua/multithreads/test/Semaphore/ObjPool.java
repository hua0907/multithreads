package com.hua.multithreads.test.Semaphore;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

public class ObjPool<T, R> {
    final List<T> pool;
    final Semaphore sem;

    public ObjPool(int size, T t) {
        pool = new Vector<T>();
        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
        sem = new Semaphore(size);
    }

    R exec(Function func) throws InterruptedException {
        T t = null;
        sem.acquire();
        try {
            t = pool.remove(0);
            return (R) func.apply(t);
        } finally {
            pool.add(t);
            sem.release();
        }
    }

    // 创建对象池
    public static void main(String[] args) throws InterruptedException {
        ObjPool pool = new ObjPool(10, 2);// 通过对象池获取t，之后执行
        pool.exec(t -> { System.out.println(t); return t.toString();});
    }

}
