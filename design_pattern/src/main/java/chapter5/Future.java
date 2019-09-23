package chapter5;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-04 09:58
 **/
public interface Future<T> {
    T get() throws InterruptedException;
}
