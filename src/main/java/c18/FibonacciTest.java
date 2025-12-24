package c18;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author Wusd
 * @date 2025/12/16
 * @description
 */
public class FibonacciTest {
    // 1. 继承RecursiveTask
    class Fibonacci extends RecursiveTask<Integer> {
        int n;
        public Fibonacci(int n) {
            this.n = n;
        }

        // 2. 实现compute
        protected Integer compute() {
            if (n <= 1) {
                return n;
            } else {
                Fibonacci f1 = new Fibonacci(n -1);
                // 2.1.1 fork1
                f1.fork();
                Fibonacci f2 = new Fibonacci(n - 2);
                // 2.1.2 fork2
                f2.fork();
                // f(n) = f(n-1) + f(n-2)
                // 2.2 join
                return f1.join() + f2.join();
            }
        }
    }

    public void testFib() throws ExecutionException, InterruptedException {
        // 3. ForkJoinPool管理线程和任务
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println("CPU核数: " + Runtime.getRuntime().availableProcessors());

        long start = System.currentTimeMillis();
        // 4. 初始化任务
        Fibonacci fibonacci = new Fibonacci(40);
        // 5. 提交任务
        Future<Integer> future = forkJoinPool.submit(fibonacci);
        // 6. 获取结果
        System.out.println(future.get());
        long end = System.currentTimeMillis();

        System.out.println("耗时: " + (end - start));
    }

    public static void main(String[] args) throws Exception {
        FibonacciTest test = new FibonacciTest();
        test.testFib();
    }
}
