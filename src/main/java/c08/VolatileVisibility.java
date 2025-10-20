package c08;

/**
 * @author Wusd
 * @date 2025/10/20
 * @description
 */
public class VolatileVisibility {
    int a = 0;
    //    volatile
    boolean flag = false;

    public void write() {
        a = 1;
        flag = true;
    }

    public void read() {
        if (flag) {
            int i = a;
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws Exception {
        VolatileVisibility vs = new VolatileVisibility();
        new Thread(() -> {
            for (int i = 0; i<1000; i++) {
                System.out.println(i);
                vs.read();
            }
        }).start();
//        Thread.sleep(100);
        new Thread(vs::write).start();
    }
}
