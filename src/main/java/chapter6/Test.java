package chapter6;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-07 08:16
 **/
public class Test {
    public static void main(String[] args) throws InterruptedException {
        ThreadService threadService = new ThreadService();
        threadService.excute(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务线程执行完毕，执行时间1s");
        });
        threadService.shutDownService(5000L);
    }
}
