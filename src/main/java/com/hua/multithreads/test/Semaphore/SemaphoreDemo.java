package com.hua.multithreads.test.Semaphore;

import java.util.Queue;

public class SemaphoreDemo {

    private int count;
    private Queue queue;

    public SemaphoreDemo(int count) {
        this.count = count;
    }

    private void down() {
        this.count--;
        if (this.count < 0) {
            System.out.println("当前线程放入等待队列");
            System.out.println("阻塞当前线程");
        }
    }

    private void up() {
        this.count++;
        if (this.count <= 0) {
            System.out.println("从等待队列移除这个线程");
            System.out.println("从等待队列唤醒一个线程");
        }
    }

}
