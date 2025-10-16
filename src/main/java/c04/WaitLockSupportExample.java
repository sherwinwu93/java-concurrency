package c04;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport.lock()阻塞当前线程,然后被其他线程以LockSupport.unpack(thread)唤醒
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class WaitLockSupportExample {
    static class Worker implements Runnable {
        private Thread mainThread;
        public Worker(Thread mainThread) {
            this.mainThread = mainThread;
        }
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 开始工作");
            f(39);
            System.out.println(Thread.currentThread().getName() + " 工作完成,唤醒主线程");

            // 工作完成后唤醒主线程
            LockSupport.unpark(mainThread);
        }
        private int f(int N) {
            if (N == 1) return 0;
            if (N == 2) return 1;
            return f(N - 1) + f(N - 2);
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread mainThread = Thread.currentThread();
        Thread worker = new Thread(new Worker(mainThread), "worker");
        System.out.println("before start " + worker.getName() + ":" + worker.getState());
        worker.start();
        Thread.sleep(100);
        System.out.println("before park " + worker.getName() + ":" + worker.getState());

        // 主线程park自己,等待worker完成
        System.out.println("主线程即将park");
        LockSupport.park();
        System.out.println("主线程被唤醒");

        System.out.println("after unpark " + worker.getName() + ":" + worker.getState());
    }
}
