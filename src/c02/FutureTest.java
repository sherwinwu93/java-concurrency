package c02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author Wusd
 * @date 2025/10/15
 * @description
 */
public class FutureTest {
    // future实现非常复杂,所以不实现了
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<?> result = executor.submit(() -> {
            try {
                Thread.sleep(1000);
                return 2;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread.sleep(1100);
        boolean cancel = result.cancel(true);
        if (cancel) {
            System.out.println(result.isCancelled());
        } else {
            Object i = result.get(2, TimeUnit.SECONDS);
            System.out.println(i);
        }
    }
}
