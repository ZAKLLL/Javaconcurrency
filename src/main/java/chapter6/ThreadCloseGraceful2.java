package chapter6;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-06 21:32
 **/
public class ThreadCloseGraceful2 {
    private static class Worker extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("中断，结束线程");
                    break; //当捕获到中断信号时候跳出循环
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        worker.start();
        Thread.sleep(5000);
        worker.interrupt();
    }
}
