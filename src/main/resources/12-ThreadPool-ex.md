# 线程池
1. 核心线程池: 最小线程数,固定不变,不被回收,corePoolSize设置
2. 最大线程池: 最大线程数,当前线程小于最大线程且任务队列满了会新建,空闲会被回收,maximumPoolSize设置
3. 任务队列: 无界队列(数量不限制),有界队列(数量有限制),同步移交队列(只缓存队列一次),优先级队列(按优先级提交任务)
4. 线程工厂: 创建新线程
5. 拒绝策略:
   1. AbortPolicy: 抛出异常(默认)
   2. CallerRunsPolicy: 提交任务的线程执行
   3. DiscardPolicy: 丢弃任务
   4. DiscardOldestPolicy: 丢弃最老任务
# 提交任务
提交任务:
1. 如果核心线程没满,则创建核心线程执行任务
2. 如果任务队列没满,则放入任务队列
3. 如果线程没满,则创建非核心线程执行任务
4. 按拒绝策略处理
# 线程池生命周期
路径1: shutdown: RUNNING->SHUTDOWN->TIDING->TERMINATED
路径2: shutdownNow: RUNNING->STOP->TIDING->TERMINATED
- RUNNING:
- SHUTDOWN: 会把排队的任务执行完毕,再进入TIDING
- STOP: 会中止正在进行的任务,再进入TIDING
- TIDYING: 执行最后的清理
- TERMINATED: 清理完毕
# 线程池配置示例
```java
@Configuration
public class ThreadPoolConfig {
    @Bean("pullDataFromTerminalPlanTaskExecutor")
    public Executor pullDataFromTerminalPlanTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(5);
        // 最大线程数
        executor.setMaxPoolSize(10);
        // 队列容量
        executor.setQueueCapacity(100);
        // 设置线程名称前缀
        executor.setThreadNamePrefix("pullDataFromTerminalPlanTaskExecutor-");
        // 拒绝策略: 调用者运行策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 任务的doAround
        executor.setTaskDecorator(new ThreadLocalTransferDecorator());
        // 初始化线程池
        executor.initialize();
        return executor;
    }
}
```
# TaskDecorator
任务执行的装饰类,在每次任务执行前后,执行这些代码.
可以用于线程上下文的传递,比如: 上下文的登录用户~token等等

# 四种内置线程池
## 1. newCachedThreadPool
ExecuteService es = Executors.newCachedThreadPool();
配置: 核心线程为0,同步移交队列,最大线程为Integer.max,CallerRunsPolicy, 60秒回收
结果: 任务一提交,有空闲线程就马上执行,没有空闲线程马上创建线程执行
优势: 适合任务时间多数量多

## 2. newFixedThreadPool
ExecuteService es = Executors.newFixedThreadPool(3);
配置: 核心线程n,无界队列,最大线程n,不回收!
结果: 只有核心线程执行任务,核心线程忙则进入队列等待
特点: 不回收! 适合任务时间久数量少

## 3. newSingleThreadExecutor
ExecuteService es = Executors.newSingleThreadExecutor();
配置: 核心线程1,最大线程1, 无界队列, 不回收!
结果: 单线程,串行按提交顺序来执行
特定: 不回收! 适合串行任务
## 4. newScheduledThreadPool
ExecuteService es = Executors.newScheduledThreadPool();
定长线程池,支持定时
