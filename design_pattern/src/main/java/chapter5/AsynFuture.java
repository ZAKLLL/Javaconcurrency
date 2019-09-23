package chapter5;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-04 10:04
 **/
public class AsynFuture<T> implements Future<T> {
    private volatile boolean done = false;
    private T result;

    void done(T result) {
        synchronized (this) {
            this.result = result;
            this.done = !done;
            this.notifyAll();
        }
    }

    @Override
    public T get() throws InterruptedException {
        synchronized (this) {
            while (!done) {
                this.wait();
            }
            return result;
        }
    }
}
