package chapter6;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-04 21:59
 **/
class Request {
    private final String value;

    public Request(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
