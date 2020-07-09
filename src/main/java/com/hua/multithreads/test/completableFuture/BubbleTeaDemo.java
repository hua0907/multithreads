package com.hua.multithreads.test.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class BubbleTeaDemo {
    CompletableFuture<String> c1 = CompletableFuture.supplyAsync(() -> {
        System.out.println("洗茶杯");
        sleep(1, TimeUnit.SECONDS);
        System.out.println("洗茶壶");
        sleep(2, TimeUnit.SECONDS);
        System.out.println("拿茶叶");
        sleep(2, TimeUnit.SECONDS);
        return "西湖龙井";
    });

    CompletableFuture<Void> c2 = CompletableFuture.runAsync(() -> {
        System.out.println("洗水壶");
        sleep(2, TimeUnit.SECONDS);
        System.out.println("烧开水");
        sleep(6, TimeUnit.SECONDS);
    });


    CompletableFuture<String> c3 = c2.thenCombine(c1, (__, tf) -> {
        System.out.println("c1拿到茶叶： " + tf);
        System.out.println("泡茶");
        sleep(2, TimeUnit.SECONDS);
        return "上茶: " + tf;
    });

    private void sleep(int t, TimeUnit unit) {
        try {
            unit.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void run() {
        String tea = c3.join();
        System.out.println(tea);
    }

    public static void main(String[] args) {
        new BubbleTeaDemo().run();
    }

}
