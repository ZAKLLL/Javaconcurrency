package chapter8;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-05 22:22
 **/
public class ExcuteTask implements Runnable {
    private QueryFromDbAction queryFromDbAction = new QueryFromDbAction();
    private QueryFromHttp queryFromHttp = new QueryFromHttp();

    @Override
    public void run() {

        queryFromDbAction.excute();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queryFromHttp.excute();
        final Context context = ActionContext.GetActionContext().getContext();
        System.out.println("name----->"+context.getName()+"      idCard-------->"+context.getIdCard());

    }
}
