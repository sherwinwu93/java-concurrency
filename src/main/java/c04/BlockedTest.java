package c04;

/**
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class BlockedTest {
    static class Obj {
        // synchronized: 整个方法都是同步方法
        private synchronized void f() {
            try {
                // 在这里打个断点,就可以看到blocked
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Obj obj = new Obj();
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                obj.f();
            }
        }, "a");
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                obj.f();
            }
        }, "b");
        a.start();
        b.start();
        System.out.println(a.getName() + ":" +a.getState());
        System.out.println(b.getName() + ":" +b.getState());
    }
}
