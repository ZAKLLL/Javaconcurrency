package chapter8;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-05 22:24
 **/
public class QueryFromDbAction {

    public void excute() {
        String name = "alex";
        ActionContext.GetActionContext().getContext().setName(name + Thread.currentThread().getName());
    }

}
