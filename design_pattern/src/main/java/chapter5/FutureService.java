package chapter5;


import java.util.function.Consumer;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-04 10:01
 **/
class FutureService {
    <T> Future<T> submit(final FutureTask<T> task, Consumer<T> consumer) {
        AsynFuture<T> asynFuture = new AsynFuture<>();
        new Thread(() -> {
            try {
                T result = task.call();
                asynFuture.done(result);
                consumer.accept(result); //使用回调函数的方式
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        return asynFuture;

    }
}
