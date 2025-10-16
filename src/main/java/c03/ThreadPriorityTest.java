package c03;

/**
 * @author Wusd
 * @date 2025/10/15
 * @description
 */
public class ThreadPriorityTest {
    public static void main(String[] args) {
        Thread a = new Thread();
        System.out.println("默认优先级:" + a.getPriority());
        Thread b = new Thread();
        b.setPriority(Thread.MAX_PRIORITY);
        System.out.println("设置过的优先级:" + b.getPriority());
    }
}
