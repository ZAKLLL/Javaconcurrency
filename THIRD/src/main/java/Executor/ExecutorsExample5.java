package Executor;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description: 直接获取TheradPool中的Queue插入的任务依然可以执行,但是需要由其他的任务触发,execute/submit
 * @author: ZakL
 * @create: 2019-10-31 17:12
 **/
public class ExecutorsExample5 {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));

        ((ThreadPoolExecutor) executorService).getQueue().add(() -> System.out.println("直接使用Queue插入的任务"));

        IntStream.range(0,5).forEach(i-> executorService.execute(()->{
            System.out.println(Thread.currentThread().getName()+"---->"+i+"is working ");
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));



    }
}
