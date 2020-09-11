package com.hua.multithreads.test.future;

import java.util.concurrent.*;

public class BubbleTeaDemo {
    FutureTask<String> ft1 = new FutureTask<>(new Task1());

    FutureTask<String> ft2 = new FutureTask<>(new Task2(ft1));

    public static void main(String[] args) {
        new BubbleTeaDemo().run();
    }

    private void run() {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(ft1);
        pool.execute(ft2);
        try {
            System.out.println(ft2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private class Task2 implements Callable<String>{

        FutureTask<String> futureTask;

        public Task2(FutureTask<String> futureTask) {
            this.futureTask = futureTask;
        }

        @Override
        public String call() throws Exception {
            System.out.println("洗水壶");
            Thread.sleep(1000);
            System.out.println("烧开水");
            Thread.sleep(5000);

            String tea = futureTask.get();
            System.out.println("拿到茶叶是： " + tea);

            System.out.println("泡茶");
            Thread.sleep(1000);

            return "上茶：" + tea;
        }
    }

    private class Task1 implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("洗茶杯");
            Thread.sleep(1000);
            System.out.println("洗茶壶");
            Thread.sleep(2000);

            System.out.println("拿茶叶");
            Thread.sleep(2000);

            return "西湖龙井";
        }
    }
}
