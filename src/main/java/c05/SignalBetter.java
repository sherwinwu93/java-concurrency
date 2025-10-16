package c05;

/**
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class SignalBetter {
    private static final Object lock = new Object();
    private static volatile int signal = 0;

    static class ThreadA implements Runnable {
        public void run() {
            while (signal < 5) {
                synchronized (lock) {
                    if (signal % 2 == 0) {
                        System.out.println("ThreadA:" + signal);
                        signal++;
                    }
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    static class ThreadB implements Runnable {
        public void run() {
            while (signal < 5) {
                synchronized (lock) {
                    if (signal % 2 == 1) {
                        System.out.println("ThreadB:" + signal);
                        signal++;
                    }
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    static class Manager implements Runnable {
        public void run() {
            while (signal < 5) {
                synchronized (lock) {
                    lock.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
        new Thread(new Manager()).start();
    }
}
