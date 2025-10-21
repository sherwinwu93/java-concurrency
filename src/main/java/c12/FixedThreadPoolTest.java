package c12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Wusd
 * @date 2025/10/21
 * @description
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        Obj obj = new Obj();
        obj.f();
    }
}
class Obj {
    public void f() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(() -> {
            System.out.println("subf()");
        });
    }
}
