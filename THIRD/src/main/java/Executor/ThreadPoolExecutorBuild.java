package Executor;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description: ThreadPool的优雅关闭
 * @author: ZakL
 * @create: 2019-10-31 09:45
 **/
public class ThreadPoolExecutorBuild {

    private static final Random random = new Random(System.currentTimeMillis());
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(10, 13, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50), Thread::new, new ThreadPoolExecutor.AbortPolicy());
        IntStream.range(0, 20).forEach(i -> executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(10));
                System.out.println(Thread.currentThread().getName() + " [" + i + "] finish done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        executorService.shutdown(); //标记线程池中的线程，当所有的的线程都执行完毕后，线程池关闭，线程池不再接受新的任务，但是仍然会将任务队列中已有的任务执行完毕。
        //5秒钟为超时时间，判断到这时间所有任务被标记的任务是否结束完成，完成返回true，否则返回false
//        if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
//            //如果超时还有线程没有结束则强制关闭正在工作的线程，并且关闭线程池
//            executorService.shutdownNow();
//        }
        System.out.println("===============has Shutdowned========== ");
    }

}
