/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-09-26 17:34
 **/
public class T {

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> System.out.println(Thread.currentThread().getName()),"Custom Thread").start();
        Thread.sleep(1000L);
        System.out.println(Thread.currentThread().getName());
    }
}
