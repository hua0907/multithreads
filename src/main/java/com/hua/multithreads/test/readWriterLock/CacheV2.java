package com.hua.multithreads.test.readWriterLock;

import com.sun.org.apache.regexp.internal.RE;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheV2<K, V> {
    private Map<K, V> map = new HashMap<>();

    private final ReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();


    V get(K k) {
        r.lock();
        V v;
        try {
            v = map.get(k);
        } finally {
            r.unlock();
        }

        if (v != null) {
            return v;
        }

        w.lock();
        try {
            r.lock();

            try {
                v = map.get(k);
                if (v != null) {
                    return v;
                }
            }finally {
                r.unlock();
            }
            System.out.println("从数据库查到数据");
            System.out.println("放入map");
        }finally {
            w.unlock();
        }
        V result = null;
        return result;



    }

    void put(K k, V v) {
        w.lock();
        try {
            map.put(k, v);
        } finally {
            w.unlock();
        }
    }

}
