package phaser;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description: 主线程只关心某一阶段,子线程到达后进行通知，无需等待即可进入下一步
 * @author: ZakL
 * @create: 2019-10-30 16:22
 **/
public class PhaserExample5 {
    private static final Random random = new Random(System.currentTimeMillis());


    private static class ArriveTask extends Thread {
        final Phaser phaser;

        private ArriveTask(Phaser phaser,int no) {
            super(String.valueOf(no));
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(getName() + " Start Working");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " endWork Working");
            phaser.arrive(); //非block，到达后就可以进行下一轮操作
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(getName()+"keep to do other thing");
        }
    }


    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);//注册5个party
        IntStream.rangeClosed(1,4).forEach(i->new ArriveTask(phaser,i).start()); //四个Arrive
        phaser.arriveAndAwaitAdvance(); //一个arriveAndAwaitAdvance，当前面四个都到达的时候执行该方法继续执行
        System.out.println("第一阶段以及到达");
    }
}
