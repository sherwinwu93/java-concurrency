package c05;

/**
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class ThreadLocalDemo {
    static class ThreadA implements Runnable {
        private ThreadLocal<String> threadLocal;
        public ThreadA(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal;
        }
        public void run() {
            threadLocal.set("A");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("ThreadA变量副本是:" + threadLocal.get());
        }
    }
    static class ThreadB implements Runnable {
        private ThreadLocal<String> threadLocal;
        public ThreadB(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal;
        }
        public void run() {
            threadLocal.set("B");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("ThreadB变量副本是:" + threadLocal.get());
        }
    }

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        new Thread(new ThreadA(threadLocal)).start();
        new Thread(new ThreadB(threadLocal)).start();
    }
}
