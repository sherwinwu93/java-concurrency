package c05;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现线程A输出0,线程B输出1,线程A输出2...循环
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class Signal {
    private static volatile int signal = 0;

    static class ThreadA implements Runnable {
        public void run() {
            while (signal < 5) {
                if (signal % 2 == 0) {
                    System.out.println("ThreadA:" + signal);
                    synchronized (this) {
                        signal++;
                    }
                }
            }
        }
    }
    static class ThreadB implements Runnable {
        public void run() {
            while (signal < 5) {
                if (signal % 2 == 1) {
                    System.out.println("ThreadB:" + signal);
                    synchronized (this) {
                        signal++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Thread(new ThreadA()).start();
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();
    }
}
