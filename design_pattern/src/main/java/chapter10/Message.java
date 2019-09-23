package chapter10;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-06 22:31
 **/
public class Message {
    private String data;

    public Message(String data) {
        this.data = data;
    }
    public String getData() {
        return data;
    }
}
