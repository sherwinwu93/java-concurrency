package c04;

/**
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class ThreadStateTest {
    public static void main(String[] args) {
        testStateNew();
    }
    // NEW: 创建线程,但未启动
    private static void testStateNew() {
        Thread t = new Thread(() -> {});
        System.out.println(t.getState());
    }
}
