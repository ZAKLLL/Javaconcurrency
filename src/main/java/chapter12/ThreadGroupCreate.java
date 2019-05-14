package chapter12;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-13 10:12
 **/
public class ThreadGroupCreate {
    public static void main(String[] args) {
        ThreadGroup tg1 = new ThreadGroup("TG1");
//        Thread t1 = new Thread(tg1,() -> {
//            while (true) {
//                try {
//                    System.out.println(Thread.currentThread().getThreadGroup().getName());
//                    System.out.println(Thread.currentThread().getThreadGroup().getParent());
//                    Thread.sleep(10_000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        },"t1");
//        t1.start();

        //把tg2放入tg1
        ThreadGroup tg2 = new ThreadGroup(tg1, "tg2");
        System.out.println(tg2.getName());
        System.out.println(tg2.getParent().getName());

    }
}
