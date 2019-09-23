package chapter14;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-17 22:11
 **/
public class PreThreadClient {
    public static void main(String[] args) {
        final MessageHandler messageHandler = new MessageHandler();
        Stream.of("Message1", "Message2", "Message3").forEach(i -> messageHandler.request(new Message(i)));
    }
}
