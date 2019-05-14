package chapter6;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-06 21:57
 **/
public class ThreadService {
    private static Boolean finished = false;
    private Thread executeThread;

    public void excute(Runnable task)  {
        executeThread = new Thread(){
            @Override
            public void run() {
                Thread taskThread = new Thread(task);
                taskThread.setDaemon(true); //将任务线程作为守护线程
                taskThread.start();
                try {
                    taskThread.join();
                    finished = true;  //当任务线程执行完毕后将标志位标志为true
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executeThread.start();
    }
    //todo 正常执行完毕后是怎样中断的
    public void shutDownService(Long mills) {
        long currentTime = System.currentTimeMillis();
        while (!finished) {
            if (System.currentTimeMillis() - currentTime > mills) {
                System.out.println("任务超时需要中断！");
                executeThread.interrupt();
                break;
            }
            //当线程还没执行完毕且没超过限定时间时
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断");
                break;
            }
        }
        finished = false;
    }
}
