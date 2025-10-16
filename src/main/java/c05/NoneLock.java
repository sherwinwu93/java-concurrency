package c05;

/**
 * 无锁,多个线程是乱序的
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class NoneLock {
    static class ThreadA implements Runnable {
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("ThreadA:" + i);
            }
        }
    }

    static class ThreadB implements Runnable {
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("ThreadB:" + i);
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
