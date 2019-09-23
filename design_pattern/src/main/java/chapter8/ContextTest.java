package chapter8;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-05 22:28
 **/
public class ContextTest {
    final Context context = new Context();
    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(i -> new Thread(new ExcuteTask()).start());
    }
}
