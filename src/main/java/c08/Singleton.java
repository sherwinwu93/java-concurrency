package c08;

/**
 * @author Wusd
 * @date 2025/10/20
 * @description
 */
public class Singleton {
    // 必须使用volatile
    volatile
    private static Singleton instance;

    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
    // instance = new Singleton()的实际步骤
    // memory=allocate();//分配内存
    // ctorInstanc(memory);//初始化对象
    // s=memory //设置s指向内存地址
    // 如果不使用volatile,可能被重排序为1-3-2

    // 线程A执行了1-3,没有执行2
    // 线程B执行了instance== null, 发现instance不为空, 返回了一个未初始化完成的instance
    // 所以需要双重锁
}
