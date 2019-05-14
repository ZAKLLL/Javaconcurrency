package chapter7;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-09 08:35
 **/
public class SynchronizedStatic {
    static {
        //class锁
        synchronized (SynchronizedStatic.class) {
            System.out.println("static" + Thread.currentThread().getName());
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized static void m1() {
        System.out.println("m1 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized static void m2() {
        System.out.println("m2 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void m3() {
        System.out.println("m3 " + Thread.currentThread().getName());
        try {
            Thread.sleep(5_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
