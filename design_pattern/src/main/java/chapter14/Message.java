package chapter14;


/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-17 22:01
 **/

public class Message {
    private final String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
