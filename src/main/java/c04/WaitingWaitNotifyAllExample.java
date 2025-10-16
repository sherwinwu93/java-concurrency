package c04;

/**
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class WaitingWaitNotifyAllExample {
    private final static Object lock = new Object();
    private static boolean condition = false;

    static class Worker implements Runnable {
        public void run() {
            synchronized (lock) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                condition = true;
                lock.notifyAll();
                System.out.println(Thread.currentThread().getName() + " 完成了工作并通知所有线程");
            }
        }
    }

    public static void main(String[] args) {
        Thread worker = new Thread(new Worker(), "worker");
        worker.start();
        System.out.println(worker.getName() + ":" + worker.getState());

        Thread a = new Thread(() -> {
            synchronized (lock) {
                while (!condition) {
                    System.out.println("线程a等待...");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程a被唤醒");
            }
        }, "a");
        Thread b = new Thread(() -> {
            synchronized (lock) {
                while (!condition) {
                    System.out.println("线程b等待...");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程b被唤醒");
            }
        }, "b");
        a.start();
        b.start();
    }
}
