package com.hua.multithreads.test.future;

import lombok.Data;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //callable接口
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1 + 2);
        executorService.submit(futureTask);

        Integer r = futureTask.get();
        System.out.println(r);

        //runnable接口
        ResultVo resultVo = new ResultVo();
        FutureTask<ResultVo> ft = new FutureTask<>(new Task(resultVo), resultVo);
        executorService.submit(ft);

        Integer a = ft.get().getA();
        System.out.println(a);


    }


    private static class Task implements Runnable{

        private ResultVo resultVo;

        public Task(ResultVo resultVo) {
            this.resultVo = resultVo;
        }

        @Override
        public void run() {
            resultVo.setA(1 + 2);
        }
    }
    @Data
    private static class ResultVo{
        private Integer a;
    }

}
