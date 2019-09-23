package chapter7;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-05 21:27
 **/
public class ThreadLocalSimpelTest {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("ss");
        Thread.sleep(100);
        System.out.println(threadLocal.get());

    }
}
