package c01;

/**
 * @author Wusd
 * @date 2025/10/15
 * @description
 */
public class Demo2 {
    public static class Task implements Runnable {
        public void run() {
            System.out.println("MyThread");
        }
    }

    public static void main(String[] args) {
        // 第一种方式
        new Thread(new Task()).start();

        // 第二种方式
        new Thread(() -> {
            System.out.println("Anonymous MyThread");
        }).start();
    }
}
