package chapter8;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-05 22:26
 **/
public class QueryFromHttp {
    public void excute() {
        ActionContext.GetActionContext().getContext().setIdCard("zzzzzzzzzzzzzzzz" + Thread.currentThread().getId());
    }
}
