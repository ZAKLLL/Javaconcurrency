package Executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-11-11 17:24
 **/
public class CompletableExample3 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        getNow();
        complete();
    }

    private static void getNow() throws InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        });
        String now = stringCompletableFuture.getNow("NOWWWWWWWWWWWWWWWWWWWWWWWw");
        System.out.println(now);
        TimeUnit.SECONDS.sleep(3);
        now = stringCompletableFuture.getNow("NOWWWWWWWWWWWWWWWWWWWWWWWw");
        System.out.println(now);
    }
    private static void complete() throws InterruptedException, ExecutionException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "World";
        });
//        TimeUnit.SECONDS.sleep(3);
        boolean hello = stringCompletableFuture.complete("HELLO");
        System.out.println(hello);
        System.out.println(stringCompletableFuture.get());
//        stringCompletableFuture.join()
    }
}

