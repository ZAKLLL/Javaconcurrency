package phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @program: javaconcurrency
 * @description: 通过铁人三项模拟phaser功能,利用phaser实现CyclicBarrier的功能
 * @author: ZakL
 * @create: 2019-10-30 10:40
 **/
public class PhaserExample2 {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);
        for (int i = 1; i < 6; i++) {
            new Athletes(i, phaser).start();
        }
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

                System.out.println(no + ": start Running");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(no + ": end Running");
                phaser.arriveAndAwaitAdvance();
                System.out.println("Phaser->:" + phaser.getPhase());

                System.out.println(no + ": start bicycle");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(no + ": end bicycle");
                phaser.arriveAndAwaitAdvance();
                System.out.println("Phaser->:" + phaser.getPhase());


                System.out.println(no + ": start jump");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(no + ": end jump");
                phaser.arriveAndAwaitAdvance();
                System.out.println("Phaser->:" + phaser.getPhase());


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
