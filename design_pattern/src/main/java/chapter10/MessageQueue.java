package chapter10;

import java.util.LinkedList;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-06 22:31
 **/
public class MessageQueue {
    private final LinkedList<Message> messages;

    private final static int Default_Max_Limit = 100;
    private final int limit;

    public MessageQueue() {
        this(Default_Max_Limit);
    }

    public MessageQueue(int limit) {
        this.messages = new LinkedList<>();
        this.limit = limit;
    }

    public void put(final Message message) throws InterruptedException {
        synchronized (messages) {
            while (messages.size() > limit) {
                messages.wait();
            }
            messages.addLast(message);
            messages.notifyAll();
        }
    }

    public Message take() throws InterruptedException {
        synchronized (messages) {
            while (messages.isEmpty()) {
                messages.wait();
            }
            Message message = messages.removeFirst();
            messages.notifyAll();
            return message;
        }
    }

    public int getMaxLimit() {
        return limit;
    }

    public int getQueueSize() {
        synchronized (messages) {
            return messages.size();
        }
    }

}
