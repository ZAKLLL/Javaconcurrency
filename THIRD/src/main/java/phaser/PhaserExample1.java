package phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description: 使用Phaser实现Countdownlatch的功能
 * @author: ZakL
 * @create: 2019-10-30 10:12
 **/
public class PhaserExample1 {

    private final static Random random = new Random(System.currentTimeMillis());

    static class Task extends Thread {
        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;
            this.phaser.register(); //将该party进行注册,表明该线程是要参与相互等待的
            start();
        }

        @Override
        public void run() {
            System.out.println("The Worker [" + getName() + "] is Working...");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance(); //等待所有被注册的party到达，blocking
        }
    }

    public static void main(String[] args) {
        final Phaser phaser = new Phaser();
        IntStream.rangeClosed(1, 5).boxed().map(i -> phaser).forEach(Task::new);
        phaser.register();
        phaser.arriveAndAwaitAdvance();
        System.out.println("All of worker finished well");
    }
}

