package com.hua.multithreads.test.readWriterLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private Map<K, V> map = new HashMap<>();

    private final ReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();


    V get(K k) {
        r.lock();
        try {
            return map.get(k);
        } finally {
            r.unlock();
        }
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
