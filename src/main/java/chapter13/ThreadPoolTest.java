package chapter13;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-06-25 09:06
 **/
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor(); //单例线程，线程池中只有一个线程在工作。
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool(); //缓存线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10); //固定型线程池，规定了线程池中的线程的最大数量
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10); //调度型线程池，根据任务列表进行延迟执行，适合执行周期性工作


    }

    private static class MyCallable implements Callable<Map<String,Object>> {
        @Override
        public Map<String, Object> call() throws Exception {
            return null;
        }
    }

}
