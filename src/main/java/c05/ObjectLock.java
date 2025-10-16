package c05;

/**
 * 加锁时,线程按顺序执行(线程同步)
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class ObjectLock {
    private static Object lock = new Object();
    static class ThreadA implements Runnable {
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("ThreadA:" + i);
                }
            }
        }
    }
    static class ThreadB implements Runnable {
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("ThreadB:" + i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread a = new Thread(new ThreadA());
        Thread b = new Thread(new ThreadB());
        a.start();
        b.start();
    }
}
