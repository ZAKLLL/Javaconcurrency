package chapter6;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-06 20:04
 **/
public class ThreadInterrupt {
    private static final Object obj = new Object();
    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (obj) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.out.println("t线程被打断");
                        }

                    }
                }
            }
        };
        t.start();
        Thread currentThread = Thread.currentThread();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t.interrupt(); //使用这个方法打断的是t线程
                currentThread.interrupt(); //使用这个方法是打断下面的t.join()的main线程
                System.out.println("interrupt");
            }
        };
        t2.start();
        try {
            t.join(); //join的是t的父线程main线程
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(Thread.currentThread()+"线程被打断");
        }
    }
}
