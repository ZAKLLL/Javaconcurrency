package Executor;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-11-03 10:03
 **/
public class RejectedExecutionExample {
    public static void main(String[] args) throws InterruptedException {
//        ExecutorService threadPool = RejectedExecutionHandlerTest(new ThreadPoolExecutor.AbortPolicy());//直接拒绝,抛出 RejectedExecutionException异常
//        ExecutorService threadPool = RejectedExecutionHandlerTest(new ThreadPoolExecutor.CallerRunsPolicy());// 当任务添加到线程池中被拒绝时，会在线程池当前正在运行的Thread线程池中处理被拒绝的任务。
        ExecutorService threadPool = RejectedExecutionHandlerTest(new ThreadPoolExecutor.DiscardOldestPolicy());// 当任务添加到线程池中被拒绝时，线程池会放弃等待队列中最旧的未处理任务，然后将被拒绝的任务添加到等待队列中。
//        ExecutorService threadPool = RejectedExecutionHandlerTest(new ThreadPoolExecutor.DiscardPolicy());//直接拒绝
        IntStream.range(0, 4).forEach(i -> threadPool.execute(() -> {
            System.out.println("Thread--> " + i + "  is working");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread--> " + i + "  work done");
        }));
        TimeUnit.SECONDS.sleep(2);
        threadPool.execute(() -> System.out.println("Power By: " + Thread.currentThread().getName()));
        threadPool.shutdown();
        if (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
            threadPool.shutdownNow();
        }
    }

    public static ExecutorService RejectedExecutionHandlerTest(RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new LinkedBlockingDeque<>(2), Executors.defaultThreadFactory(), handler);
    }
}
