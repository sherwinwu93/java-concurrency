package c04;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class TimedWaitingLockSupportExample {
    static class Worker implements Runnable {
        private Thread mainThread;
        public Worker(Thread mainThread) {
            this.mainThread = mainThread;
        }
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 运行开始");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LockSupport.unpark(mainThread);
            System.out.println(Thread.currentThread().getName() + "运行完毕,且unpark了主线程");
        }
    }
    public static void main(String[] args) throws Exception{
        Thread worker = new Thread(new Worker(Thread.currentThread()), "worker");
        worker.start();
        System.out.println("主线程开始park");
        long start = System.currentTimeMillis();
        // park>2000
//        LockSupport.parkUntil(start + 3000);
        // park<2000
        LockSupport.parkUntil(start + 1000);
        System.out.println((System.currentTimeMillis() - start) / 1000 + "秒后" + "主线程被唤醒");
    }
}
