package c01;

/**
 * @author Wusd
 * @date 2025/10/15
 * @description
 */
public class Demo {
    public static class MyThread extends Thread {
        public void run() {
            System.out.println("MyThread");
        }
    }

    public static void main(String[] args) {
        Thread t = new MyThread();
        t.start();
    }
}
