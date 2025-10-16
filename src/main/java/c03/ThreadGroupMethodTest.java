package c03;

/**
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class ThreadGroupMethodTest {
    public static void main(String[] args) {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        // 获取当前线程组名称
        System.out.println("线程组名称:"+ threadGroup.getName());

        // 复制线程组
        Thread[] threads = new Thread[threadGroup.activeCount()];
        ThreadGroup copyedThreadGroup = new ThreadGroup("copyedThreadGroup");
        copyedThreadGroup.enumerate(threads);

        // 线程组统一异常处理
        ThreadGroup threadGroup1 = new ThreadGroup("group1") {
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + ": " + e.getMessage());
            }
        };
        Thread thread1 = new Thread(threadGroup1, new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("测试异常");
            }
        });
        thread1.start();
    }
}
