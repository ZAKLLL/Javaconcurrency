package exchanger;

import java.util.concurrent.Exchanger;

/**
 * @program: javaconcurrency
 * @description: 成对线程互相交换对象
 * @author: ZakL
 * @create: 2019-10-27 10:08
 **/
public class ExchaneExample {
    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();
        new Thread(new Runnable() {
            String a = "Thread-A";
            @Override
            public void run() {
                try {
                    System.out.println("Thread-A:"+exchanger.exchange(a));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread-A").start();
        new Thread(new Runnable() {
            String a = "Thread-B";
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()  + exchanger.exchange(a));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread-B").start();
    }
}
