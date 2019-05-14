package chapter10;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-12 14:49
 **/
public class SynchronizedProblem {
    public static void main(String[] args) {
        new Thread(SynchronizedProblem::run).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(SynchronizedProblem::run).start();

    }

    private synchronized static void run() {
        System.out.println(Thread.currentThread().getName());
        while (true) {
        }
    }
}
