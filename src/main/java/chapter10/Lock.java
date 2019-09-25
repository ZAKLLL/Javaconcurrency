package chapter10;

import java.util.Collection;
import java.util.Collections;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-12 10:55
 **/
public interface Lock {
    class TimeOutException extends Exception {
        public TimeOutException(String str) {
            super(str);
        }
    }

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeOutException;

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlockSedSize();
}
