package com.hua.multithreads.test.msb;

public class ThreadState {

    static class myThread extends Thread {
        @Override
        public void run() {
            System.out.println("t: " + this.getState());

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        myThread t = new myThread();
        System.out.println("begin: " + t.getState());
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t.getState());

    }


}
