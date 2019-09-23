package chapter5;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-04 10:00
 **/
@FunctionalInterface
public interface FutureTask<T> {
    T call() throws InterruptedException;
}
