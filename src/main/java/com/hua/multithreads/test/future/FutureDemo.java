package com.hua.multithreads.test.future;

import lombok.Data;

import java.util.concurrent.*;

public class FutureDemo {


    public static void main(String[] args) {
        new FutureDemo().test();

    }

    private void test() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        Result result = new Result();
        result.setA(101);

        Future<Result> future = pool.submit(new Task(result), result);
        try {
            Result r = future.get();
            System.out.println(r.getA());
            System.out.println(r.getB());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


    }

    private class Task implements Runnable {

        private Result result;

        public Task(Result result) {
            this.result = result;
        }

        @Override
        public void run() {
            result.setB("bbbbb");
        }
    }


    @Data
    private class  Result{
        private int a;
        private String b;
    }
}
