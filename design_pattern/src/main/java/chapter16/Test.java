package chapter16;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-26 21:27
 **/
public class Test {
    public static void main(String[] args) {
        Channel channel = new Channel(5);
        channel.startWork();

        new TransportThread("ZHOUZHENZHAO", channel).start();
        new TransportThread("WUZERUI", channel).start();
        new TransportThread("XUEZIJIAN", channel).start();
    }
}
