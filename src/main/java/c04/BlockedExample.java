package c04;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程A和线程B执行同步代码块,线程A先执行,线程B等待锁->BLOCKED
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
@Slf4j
public class BlockedExample {
    static class Obj {
        // synchronized: 整个方法都是同步方法
        private synchronized void f() {
            f(39);
        }

        private int f(int N) {
            if (N == 1) return 0;
            if (N == 2) return 1;
            return f(N - 1) + f(N - 2);
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
        log.info(a.getName() + ":" +a.getState());
        log.info(b.getName() + ":" +b.getState());
    }
}
