package Executor;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description: cacheThreadPool的使用
 * @author: ZakL
 * @create: 2019-10-31 16:44
 **/
public class ExecutorsExample1 {
    public static void main(String[] args) throws InterruptedException {
        useCachedThreadPool();
    }

    private static void useCachedThreadPool() throws InterruptedException {
        //默认只有一个线程，当一个线程执行周期内没有执行完毕后，有新的任务进来时会创建新的线程来工作
        //These pools will typically improve the performance of programs that execute many short-lived asynchronous tasks.
        //适合短时间工作的任务
        ExecutorService executorService = Executors.newCachedThreadPool();

        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        executorService.execute(() -> System.out.println("============"));

        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());


        IntStream.rangeClosed(1, 100).forEach(i -> executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " [" + i + "] ");
        }));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
    }

}
