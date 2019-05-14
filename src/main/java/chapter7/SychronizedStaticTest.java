package chapter7;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-09 08:37
 **/
public class SychronizedStaticTest {
    public static void main(String[] args) {
        new Thread("T1") {
            @Override
            public void run() {
                SynchronizedStatic.m1();
            }
        }.start();
        new Thread("T2") {

            @Override
            public void run() {
                SynchronizedStatic.m2();
            }
        }.start();
        new Thread("T3") {
            @Override
            public void run() {

                SynchronizedStatic.m3();

            }
        }.start();
    }
}
