package c04;

/**
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class TimedWaitingJoinExample {
    static class Worker implements Runnable {
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 开始运行");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " 运行完毕");
        }
    }
    public static void main(String[] args) throws Exception {
        Thread worker = new Thread(new Worker(), "worker");
        worker.start();
        long start = System.currentTimeMillis();
        //超时时间>2000
//        worker.join(3000);
        //超时时间<2000
        worker.join(1000);
        System.out.println("主线程到此花费" + (System.currentTimeMillis() - start) / 1000 + '秒');
    }
}
