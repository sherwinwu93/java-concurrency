package c05;

/**
 * wait和notify必须使用同一对象
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class WaitAndNotify {
    private static Object lock = new Object();

    static class ThreadA implements Runnable {
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("ThreadA:" + i);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }
    }
    static class ThreadB implements Runnable {
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("ThreadB:" + i);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Thread a = new Thread(new ThreadA());
        Thread b = new Thread(new ThreadB());
        a.start();
        Thread.sleep(1000);
        b.start();
    }
}
