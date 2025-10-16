package c04;

/**
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class JoinTest {
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
        a.join();
        b.start();
        // 因为a.join(),所以主线程会等待a线程结束,再继续执行
        System.out.println(a.getName() + ":" +a.getState());
        System.out.println(b.getName() + ":" +b.getState());
    }
}
