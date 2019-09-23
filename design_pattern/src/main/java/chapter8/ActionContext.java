package chapter8;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-05 22:45
 **/
public class ActionContext {
    private final ThreadLocal<Context> contextThreadLocal = ThreadLocal.withInitial(Context::new);
    //单例模式获取
    private static class ContextHolder{
        private static final ActionContext actionContext = new ActionContext();
    }

    public static ActionContext GetActionContext() {
        return ContextHolder.actionContext;
    }

    public Context getContext() {
        return contextThreadLocal.get();
    }
}
