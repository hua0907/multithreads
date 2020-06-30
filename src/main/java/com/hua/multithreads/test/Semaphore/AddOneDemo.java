package com.hua.multithreads.test.Semaphore;

import java.util.concurrent.Semaphore;

public class AddOneDemo {
    static int count;
    Semaphore semaphore = new Semaphore(1);

    private void add() throws InterruptedException {
        semaphore.acquire();
        try {
            count++;
        }finally {
            semaphore.release();
        }
    }

}
