package c05;

import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author Wusd
 * @date 2025/10/16
 * @description
 */
public class Pipe {
    static class ReaderThread implements Runnable {
        private PipedReader reader;
        public ReaderThread(PipedReader reader) {
            this.reader = reader;
        }
        public void run() {
            // 1. ReaderThread先执行
            System.out.println("this is reader");
            int receive = 0;
            try {
                // 2. reader.read()阻塞线程
                while ((receive = reader.read()) != -1) { // 7. -1表示管道关闭
                    // 5. reader.read()得到字符
                    System.out.print((char) receive);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    static class WriterThread implements Runnable {
        private PipedWriter writer;
        public WriterThread(PipedWriter writer) {
            this.writer = writer;
        }
        public void run() {
            // 3. writerThread开始执行
            System.out.println("this is writer");
            try {
                // 4. writer.write("test")往管道写入字符串
                writer.write("test");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    // 6. writer.close()关闭管道
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();
        // 注意一定要连接,才能通信
        writer.connect(reader);

        new Thread(new ReaderThread(reader)).start();
        Thread.sleep(1000);
        new Thread(new WriterThread(writer)).start();
    }
}
