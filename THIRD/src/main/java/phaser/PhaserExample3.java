package phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-10-30 10:40
 **/
public class PhaserExample3 {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);
        for (int i = 1; i < 5; i++) {
            new Athletes(i, phaser).start();
        }
        new AthletesHurt(5, phaser).start();
    }

    private static void sport(Phaser phaser, String x, String x2) throws InterruptedException {
        System.out.println(x);
        TimeUnit.SECONDS.sleep(random.nextInt(5));
        System.out.println(x2);
        phaser.arriveAndAwaitAdvance();
    }

    static class Athletes extends Thread {
        private final int no;
        private final Phaser phaser;

        Athletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {

                sport(phaser, no + ": start Running", no + ": end Running");
                sport(phaser, no + ": start Bicycle", no + ": end Bicycle");
                sport(phaser, no + ": start Jumping", no + ": end Jumping");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class AthletesHurt extends Thread {
        private final int no;
        private final Phaser phaser;

        AthletesHurt(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {

                sport(phaser, no + ": start Running", no + ": end Running");
                sport(phaser, no + ": start Bicycle", no + ": end Bicycle");
                System.out.println(no+": DAMN IT , I GOT HURT ");
                phaser.arriveAndDeregister(); //表明退出，并且取消注册，其他线程无需等待这个线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
