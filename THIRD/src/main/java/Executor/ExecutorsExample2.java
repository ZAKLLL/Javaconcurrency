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
public class ExecutorsExample2 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newWorkStealingPool();
        List<Callable<String>> collect = IntStream.range(0, 20).boxed().map(i -> (Callable<String>) () -> {
            System.out.println("Thread "+Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
            return "Task: " + i;
        }).collect(Collectors.toList());

        executorService.invokeAll(collect).stream().map(stringFuture -> {
            try {
                return stringFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }).forEach(System.out::println);
    }
}
