package chapter5;

import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-06 15:51
 **/
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "-Index" + i)),"子线程一");
        Thread t2 = new Thread(() -> IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "-Index" + i)),"子线程二");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("子线程执行完毕");
        IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "-Index" + i));
    }

}
