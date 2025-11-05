package c17;

import org.apache.commons.lang3.RandomUtils;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author Wusd
 * @date 2025/11/3
 * @description
 */
public class CountDownLatchDemo {
    // 前置任务线程
    static class PreTaskThread implements Runnable {
        private String task;
        private CountDownLatch countDownLatch;

        public PreTaskThread(String task, CountDownLatch countDownLatch) {
            this.task = task;
            this.countDownLatch = countDownLatch;
        }

        public void run() {
            try {
                Thread.sleep(RandomUtils.nextInt(0, 1000));
                System.out.println(task + " - 任务完成");
                countDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 假设有三个模块需要加载
        CountDownLatch countDownLatch = new CountDownLatch(3);
        // 主任务
        new Thread(() -> {
            try {
                System.out.println("等待数据加载...");
                System.out.println(String.format("还有%d个前置任务", countDownLatch.getCount()));
                // countDownLatch为0时,才会被唤醒
                countDownLatch.await();
                System.out.println("数据加载完成,正式开始游戏!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(new PreTaskThread("模块1", countDownLatch)).start();
        new Thread(new PreTaskThread("模块2", countDownLatch)).start();
        new Thread(new PreTaskThread("模块3", countDownLatch)).start();
    }
}
