package chapter7;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-05 21:34
 **/
public class ThreadLocalSimulator <T>{
    private final Map<Thread, T> threadTMap = new HashMap<>();

    public void set(T t) {
        synchronized (this) {
            threadTMap.put(Thread.currentThread(), t);
        }
    }

    public T get() {
        synchronized (this) {
            T value = threadTMap.get(Thread.currentThread());
            if (null == value) {
                return initialValue();
            }
            return value;
        }
    }

    public T initialValue() {
        return null;
    }

}
