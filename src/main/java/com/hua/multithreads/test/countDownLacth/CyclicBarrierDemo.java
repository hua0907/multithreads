package com.hua.multithreads.test.countDownLacth;

import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {
    static Vector<Porder> pVector;
    static Vector<Dorder> dVector;

    private Executor checkPool = Executors.newSingleThreadExecutor();

    CyclicBarrier cb = new CyclicBarrier(2, () -> checkPool.execute(this::check));

    private void check() {
        Porder p = pVector.remove(0);
        Dorder d = dVector.remove(0);
        System.out.println("对账操作: " + p + "   " + d);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
        pVector = new Vector<>();
        dVector = new Vector<>();

        new CyclicBarrierDemo().f();
    }


    private void f() {

        Runnable r1 = () -> {
            int i = 0;

            while (i < 10) {
                try {
                    dVector.add(new Dorder());
                    cb.await();
                    System.out.println("订单");
//                    Thread.sleep(5000);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                i++;
            }

        };

        Runnable r2 = () -> {
            int i = 0;
            while (i < 10) {
                try {
                    pVector.add(new Porder());
                    cb.await();
                    System.out.println("物流单");
                    Thread.sleep(5000);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                i++;
            }

        };

//        Runnable r3 = () -> {
//            int i = 0;
//            while (i < 10) {
//                try {
//                    cb.await();
//                    System.out.println("测试单");
//                } catch (InterruptedException | BrokenBarrierException e) {
//                    e.printStackTrace();
//                }
//                i++;
//            }
//
//        };

        Executor runPool = Executors.newFixedThreadPool(2);
        runPool.execute(r1);
        runPool.execute(r2);
    }


    class Porder{

    }

    class Dorder{

    }
}
