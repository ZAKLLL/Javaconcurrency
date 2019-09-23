package chapter12;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-13 21:25
 **/
public class CountDown {
    private final int total;
    private int counter = 0;

    public CountDown(int total) {
        this.total = total;
    }

    public void down() {
        synchronized (this) {
            this.counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (counter != total) {
                this.wait();
            }
        }

    }
}
