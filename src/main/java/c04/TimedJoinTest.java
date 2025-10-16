package c04;

/**
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class TimedJoinTest {
    static class Obj {
        private void f() {
            try {
                // 在这里打个断点,就可以看到blocked
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws Exception{
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
        a.join(1000L);
        b.start();
        // TIMED_WAITING因为线程a Thread.sleep
        System.out.println(a.getName() + ":" +a.getState());
        // BLOCKED
        System.out.println(b.getName() + ":" +b.getState());
    }
}
