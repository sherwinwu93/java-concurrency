package c04;

/**
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class TimedWaitingWaitNotifyExample {
    private static final Object lock = new Object();

    static class Worker implements Runnable {
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (lock) {
                lock.notify();
                System.out.println(Thread.currentThread().getName() + " 完成了工作,并通知了其他线程");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread worker = new Thread(new Worker(), "worker");

        synchronized (lock) {
            worker.start();
            System.out.println("主线程休眠");
            long now = System.currentTimeMillis();
            //设置时间大于2000,让其能被子线程唤醒
//            lock.wait(3000);
            //设置时间小于2000,让其自己唤醒
            lock.wait(1000);
            System.out.println("主线程休眠时间: " + (System.currentTimeMillis() - now) / 1000);
        }
    }
}
