package chapter3;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-11 09:02
 **/
public class User extends Thread {
    private final String name;
    private final String address;
    private final Gate gate;
    public User(String name, String address,Gate gate) {
        this.name = name;
        this.address = address;
        this.gate = gate;
    }

    @Override
    public void run() {
        while (true) {
            gate.pass(name, address);
        }
    }
}
