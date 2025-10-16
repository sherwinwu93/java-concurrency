package c02;

import java.util.concurrent.*;

/**
 * @author Wusd
 * @date 2025/10/15
 * @description
 */
public class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        return 2;
    }

    // Callable搭配线程池使用
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        MyCallable task = new MyCallable();
        Future<Integer> result = executor.submit(task);
        // get()会阻塞线程,直到得到结果
//        System.out.println(result.get());
        // 实战建议增加超时时间
        System.out.println(result.get(2, TimeUnit.SECONDS));
    }
}
