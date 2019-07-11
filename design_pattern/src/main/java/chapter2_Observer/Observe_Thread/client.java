package chapter2_Observer.Observe_Thread;

import java.util.Arrays;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-08 22:27
 **/
public class client {
    public static void main(String[] args) {
        new ThreadLifeCycleObserver().concurrencyQuery(Arrays.asList("1", "2", "4"));
    }
}
