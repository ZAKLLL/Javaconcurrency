package chapter4;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-06 11:26
 **/
public class DaemonThread {
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"is running");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"is done");
            }
        }; //new 状态

        //将线程设置为守护线程，主线程结束时候，守护线程结束
        t.setDaemon(true);
        t.start(); //runnable状态 ,可能变成 ->running || ->dead || ->blocked

        System.out.println(Thread.currentThread().getName()+"执行完毕");
    }
}
