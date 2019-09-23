package chapter7;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-05 21:30
 **/
public class ThreadLocalComplexTest {
    //    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static final ThreadLocalSimulator<String> threadLocal = new ThreadLocalSimulator<String>() {
        @Override
        public String initialValue() {
            return "HI MAIN";
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            threadLocal.set("this is Thread1");
            System.out.println(threadLocal.get());
        });
        Thread t2 = new Thread(() -> {
            threadLocal.set("this is Thread2");
            System.out.println(threadLocal.get());
        }
        );
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("main Thread---------->" + threadLocal.get());

    }

}
