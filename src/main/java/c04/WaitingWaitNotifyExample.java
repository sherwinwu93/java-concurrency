package c04;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
@Slf4j
public class WaitingWaitNotifyExample {
    private static final Object lock = new Object();
    private static boolean condition = false;

    static class Worker implements Runnable {
        public void run() {
            synchronized (lock) {// 5. 子线程获取锁
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                condition = true;
                // 6. 子线程唤醒主线程
                lock.notify();
                System.out.println(Thread.currentThread().getName() + " 完成了工作并通知");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread worker = new Thread(new Worker(), "worker");

        synchronized (lock) {// 1. 主线程获取锁
            // 2. 启动子线程(子线程在BLOCKED状态)
            worker.start();
            // 3. 打印子线程状态
            System.out.println(worker.getName() + ":" + worker.getState());
            while (!condition) {// 防止被虚假唤醒
                System.out.println("主线程等待...");
                // 4. 主线程释放锁
                lock.wait();
            }
            // 7. 主线程继续执行
            System.out.println("主线程被唤醒");
        }
        System.out.println(worker.getName() + ":" + worker.getState());
    }
}