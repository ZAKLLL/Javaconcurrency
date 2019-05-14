package chapter9;

import java.util.*;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-11 15:33
 **/
public class CaptureService {
    private  static final LinkedList<Control> CONTROLS = new LinkedList<>();
    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10").forEach(t -> {
            Thread thread = createCaptureThread(t);
            threads.add(thread);
            thread.start();
        });
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static Thread createCaptureThread(String name) {
        return new Thread(() -> {
            Optional.of("The Work ["+Thread.currentThread().getName()+"] begin capture data").ifPresent(System.out::println);
            synchronized (CONTROLS) {
                while (CONTROLS.size() > 5) {
                    try {
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName()+"--capture..... data ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            CONTROLS.addLast(new Control());

            synchronized (CONTROLS){
                System.out.println(Thread.currentThread().getName()+"--capture data successfully");

                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }
        }, name);

    }
    private static class Control{

    }
}
