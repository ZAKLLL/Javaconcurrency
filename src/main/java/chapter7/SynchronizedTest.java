package chapter7;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-08 08:57
 **/
public class SynchronizedTest {
    private static ThisLock thisLock = new ThisLock();
    public static void main(String[] args) {
        new Thread("线程一") {
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();
        new Thread("线程二") {
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();
    }
}
class ThisLock {
    private final  Object obj = new Object();
    //使用this锁
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + "抢到了锁");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //对象锁，与上面的synchronized不是同一把锁
    public void m2() {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + "抢到了锁");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
