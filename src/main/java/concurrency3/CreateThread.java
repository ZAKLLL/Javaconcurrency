package concurrency3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-04 14:34
 **/
public class CreateThread {

    private static int counter = 0;

    //由jvm创建的main线程
    public static void main(String[] args) throws ParseException {
        Thread t = new Thread(null, new Runnable() {
            public void run() {
                try {
                    add(1);
                } catch (Error error) {
                    System.out.println("自定义栈大小" + counter);
                }
            }
            private void add(int i) {
                counter++;
                add(i + 1);
            }
        }, "TestStack",1<<24);
        t.start();
    }
}
