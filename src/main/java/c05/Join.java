package c05;

/**
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class Join {
    static class ThreadA implements Runnable {
        public void run() {
            try {
                System.out.println("ThreadA,准备睡一秒");
                Thread.sleep(1000);
                System.out.println("ThreadA,一秒睡完了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread a = new Thread(new ThreadA());
        a.start();
        a.join();
        System.out.println("我会比子线程晚打印");
    }
}
