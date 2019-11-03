package phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-10-30 16:22
 **/
public class PhaserExample6 {
    private static final Random random = new Random(System.currentTimeMillis());


    private static class AWaitAdvance extends Thread {
        final Phaser phaser;

        private AWaitAdvance(Phaser phaser, int no) {
            super(String.valueOf(no));
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {

            System.out.println("Phaser phase:" + phaser.getPhase());

            System.out.println(getName() + " Start Working 1");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " end Working 1");
            phaser.arriveAndAwaitAdvance();
            System.out.println("Phaser phase:" + phaser.getPhase());


            System.out.println(getName() + " Start Working 2");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " end Working 2 ");
            phaser.arriveAndAwaitAdvance();

            System.out.println("Phaser phase:" + phaser.getPhase());

        }
    }


    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);
        IntStream.rangeClosed(1, 5).forEach(i -> new AWaitAdvance(phaser, i));
        phaser.awaitAdvance(0);
        System.out.println("============");
    }
}
