package c03;

/**
 * @author Wusd
 * @date 2025/10/15
 * @description
 */
public class ThreadGroupTest {
    public static void main(String[] args) throws Exception {
        Thread testThread = new Thread(() -> {
            System.out.println("testThread的线程组名称:"
                    + Thread.currentThread().getThreadGroup().getName());
            System.out.println("testThread的线程名称:" + Thread.currentThread().getName());
        });
        testThread.start();
        Thread.sleep(1000);
        System.out.println("main线程的线程组名称:" + Thread.currentThread().getThreadGroup().getName());
        System.out.println("main线程的线程名称:" + Thread.currentThread().getName());
    }
}
