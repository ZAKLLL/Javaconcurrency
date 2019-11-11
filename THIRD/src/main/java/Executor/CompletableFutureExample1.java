package Executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-11-10 09:31
 **/
public class CompletableFutureExample1 {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Start work");
            //sleep(2);
            System.out.println("End work");
            return "res";
        }).whenComplete((r, t) -> {
            System.out.println(r);
            if (t == null) {
                System.out.println("No err");
            }
        });
        Thread.currentThread().join();
    }
}
