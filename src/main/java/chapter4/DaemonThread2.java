package chapter4;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-06 14:47
 **/
public class DaemonThread2 {
    public static void main(String[] args) {
        Thread t = new Thread("T") {
            @Override
            public void run() {
               Thread innerThread= new Thread(new Runnable() {
                    public void run() {
                        int count=0;
                        while (true) {
                            System.out.println(Thread.currentThread().getName()+"is Running"+count+++"times");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },"innerThread");
               //将InnerThread设置为守护线程
                innerThread.setDaemon(true);
                innerThread.start();
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+"is finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}
