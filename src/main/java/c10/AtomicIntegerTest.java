package c10;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Wusd
 * @date 2025/11/3
 * @description
 */
public class AtomicIntegerTest {
    public static void main(String[] args) {
        AtomicInteger atomicI = new AtomicInteger(5);
        atomicI.getAndAdd(1);
    }
}
