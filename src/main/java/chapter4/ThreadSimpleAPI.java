package chapter4;

import java.util.Optional;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-06 15:04
 **/
public class ThreadSimpleAPI {

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                Optional<String> s = Optional.of(Thread.currentThread().getName() + "-Index" + i);
                s.ifPresent(System.out::println);
            }
        },"线程一");
        t1.setPriority(Thread.MIN_PRIORITY);
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                Optional.of(Thread.currentThread().getName() + "-Index" + i).ifPresent(System.out::println);
            }
        },"线程二");
        t2.setPriority(Thread.NORM_PRIORITY);
        Thread t3 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                Optional.of(Thread.currentThread().getName() + "-Index" + i).ifPresent(System.out::println);
            }
        },"线程三");
        t3.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
    }
}
