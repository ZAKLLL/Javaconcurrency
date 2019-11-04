package Executor;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description: 使用callable
 * @author: ZakL
 * @create: 2019-10-31 17:12
 **/
public class ExecutorsExample4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> collect = IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
            System.out.println(Thread.currentThread().getName() + "---->" + i);
            return i;
        }).collect(Collectors.toList());
        Integer integer = executorService.invokeAny(collect);//当得到返回后，其他正在执行的线程会停止
        System.out.println(integer);

    }
}
