package chapter6;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-06 21:14
 **/
public class ThreadCloseGraceful1 {
    private static class Worker extends Thread {
        private volatile Boolean status = true;
        @Override
        public void run() {
            while (status) {
                System.out.println("--------");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        private void shutdown() {
            this.status = false;
        }
    }
    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.shutdown();
    }
}
