package chapter2_Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-08 21:26
 **/
public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        if (this.state == state) {
            return;
        }
        this.state = state;
        notifyAllObServer();
    }

    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    private void notifyAllObServer() {
        observers.forEach(Observer::update);
    }
}
