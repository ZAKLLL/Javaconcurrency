package Executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-11-11 10:23
 **/
public class CompletableFutureExample2 {

    public static void main(String[] args) throws InterruptedException {
//        acceptBoth();
//        acceptEither();
//        runAfterBoth();
//        thenCombine();
        thenCompose();
        Thread.currentThread().join();

    }

    //thenCompose（）用来连接两个CompletableFuture，是生成一个新的CompletableFuture,并且前一个Future.get()会成为thenCompose的参数/注意与thenApply的区分
    private static void thenCompose() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Start thenCompose 1");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End thenCompose 1");
            return "HELLO WORLD";
        }).thenCompose(i -> CompletableFuture.supplyAsync(() -> {
            System.out.println("Start thenCompose 2");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Start thenCompose 2");
            return i.length();
        })).whenComplete((i, t) -> System.out.println(i));
    }


    //combine可以用以接受一个BiFunction,调用者和传入的CompletableFuture作为BiFunction的参数,再返回一个CompletableFuture<T>,可继续操作。
    private static void thenCombine() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start thenCombine 1");
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(6));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end thenCombine 1");
            return "12345";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("start thenCombine 2");
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(6));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end thenCombine 2");
            return "6789";
        }), (i, j) -> i.length() > j.length()).whenComplete((b, t) -> System.out.println(b));
    }

    //执行完毕后调用Runnable,不消费
    private static void runAfterBoth() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start RunAfterBoth 1");
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(6));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end RunAfterBoth 1");
            return "Task 1 Result";
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("start RunAfterBoth 2");
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end RunAfterBoth 2");
            return "Task 2 Result";
        }), () -> System.out.println("Done"));
    }


    //取先完成的结果.但是没完成的任务也将继续执行
    private static void acceptEither() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Start Either1");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End Either2");
            return "Either1";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("Start Either2");
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End Either2");
            return "Either2";
        }), i -> System.out.println("Final result " + i));
    }

    //接受两个Future
    private static void acceptBoth() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Start task1");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }).thenAcceptBoth( //该函数接受前面的future.get()作为h参数传入BiConsumer,并接受本参数callable的future.get()作为w参数传入BiConsumer
                CompletableFuture.supplyAsync(() -> "World"),
                (h, w) -> System.out.println(h + "-----" + w));
    }
}
