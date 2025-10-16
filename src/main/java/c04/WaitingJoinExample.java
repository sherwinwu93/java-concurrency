package c04;

/**
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class WaitingJoinExample {
    private static final Object lock = new Object();
    static class Worker implements Runnable {
        public void run() {
            synchronized (lock) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 完成了工作");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread worker = new Thread(new Worker(), "worker");
        worker.start();
        worker.join();
        //
        System.out.println(worker.getName() + ":" + worker.getState());
    }
}
