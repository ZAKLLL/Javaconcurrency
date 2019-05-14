package concurrency1;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-04-28 21:04
 **/
public class TryConcurrency {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        t1.setName("线程t1");
        t1.start();
    }


}
