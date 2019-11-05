package Executor;

import java.util.concurrent.*;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-11-05 14:51
 **/
public class FutureTaskExample1 {
    private ExecutorService executorService = Executors.newFixedThreadPool(5);


    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        new FutureTaskExample1().t2();
    }

    public void t1() throws InterruptedException, ExecutionException, TimeoutException {
        Future<Integer> integerFuture = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("====================");
            return ThreadLocalRandom.current().nextInt(10);
        });
        //当发生超时调用线程抛出TimeoutException,线程池任务会继续执行
        Integer integer = integerFuture.get(2, TimeUnit.SECONDS);
        System.out.println("结果获取成功" + integer);
        System.out.println("successDone");
    }

    public void t2() throws InterruptedException, ExecutionException, TimeoutException {
        Future<Integer> integerFuture = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("====================");
            return ThreadLocalRandom.current().nextInt(10);
        });
        //主动中断调用线程,线程次任务正常执行完毕
        Thread callerThread = Thread.currentThread();
        new Thread(callerThread::interrupt).start();
        Integer integer = integerFuture.get();
        System.out.println("结果获取成功" + integer);
        System.out.println("successDone");
    }


}
