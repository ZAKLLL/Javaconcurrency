package atomic;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-09-23 14:58
 **/
public class GetLockException extends Exception {
    public GetLockException() {
        super();
    }

    public GetLockException(String msg) {
        super(msg);
    }
}
