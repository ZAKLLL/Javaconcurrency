package chapter7;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-07 09:18
 **/
public class TicketSell {
    public static void main(String []args){
        Tick_Runnable t1=new Tick_Runnable();
        Thread th1=new Thread(t1,"th1");
        Thread th2=new Thread(t1,"th2");
        Thread th3=new Thread(t1,"th3");

        th2.start();
        th1.start();
        th3.start();
    }
}
class Tick_Runnable implements Runnable{
    private final Object obj = new Object();
    private int tickcount=100;
    public void run(){
        synchronized (obj) {
            while(tickcount>0){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"正在卖票"+"剩余票数"+--tickcount);
            }
        }
    }
}