package phaser;

import java.sql.Time;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @program: javaconcurrency
 * @description: OnAdvance函数在Phaser中的作用
 * @author: ZakL
 * @create: 2019-10-30 14:33
 **/
public class PhaserExample4 {

    public static void main(String[] args) throws InterruptedException {
        final Phaser phaser = new Phaser(2) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                //判断是否到达
//                return super.onAdvance(phase, registeredParties);
                return true;
            }

        };
        new OnAdvanceTask(phaser, "A").start();
        new OnAdvanceTask(phaser, "B").start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(phaser.getUnarrivedParties());
        System.out.println(phaser.getArrivedParties());

    }

    static class OnAdvanceTask extends Thread {
        private final Phaser phaser;


        OnAdvanceTask(Phaser phaser, String name) {
            super(name);
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(getName() + "I am start, the phases: " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            System.out.println(getName() + "I am end .");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (getName().equals("A")) {
                System.out.println(getName() + "I am start, the phases: " + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();
                System.out.println(getName() + "I am end .");
            }

        }
    }

}
