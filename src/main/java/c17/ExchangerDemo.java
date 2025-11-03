package c17;

import java.util.concurrent.Exchanger;

/**
 * @author Wusd
 * @date 2025/11/3
 * @description
 */
public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            System.out.println("这是线程A,得到");
        });
    }
}
