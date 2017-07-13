package threadPool;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/7/13.
 */
public class ThreadPools {
    public static void main(String[] args){
        Executor threadPool = null;

        /*
         * 该方法返回一个固定线程数量的线程池,该线程池中的线程数量 是固定不变的,
         * 当一个新任务被提交时,若然没有空闲线程,则会暂时存储在一个任务队列中,
         * 待有空闲的线程便处理任务队列中的任务
         */
        threadPool = Executors.newFixedThreadPool(10);

        /*
         * 该方法返回一个只有一个线程的线程池,当一个新任务被提交后,
         * 会进入任务队列待有空闲线程时即处理任务队列中的任务
         */
        threadPool = Executors.newSingleThreadExecutor();

        /*
         * 该方法返回一个可根据实际任务数量调整线程数量的线程池,
         * 该线程池的线程数量是不固定的,若有空闲线程,则会优先处理任务,
         * 若所有线程都在执行任务时又有新的任务被提交,则会创建新的线程去处理任务
         */
        threadPool = Executors.newCachedThreadPool();

        /*
         * 每个创建线程池的方法都有ThreadFactory的重载
         */
        threadPool = Executors.newFixedThreadPool(10, new ThreadFactory() {

            @Override
            public Thread newThread(Runnable r) {
                /*
                 * 使用ThreadFactory能控制线程产生时的细节操作
                 */
                Thread thread = new Thread(r);
                // 设置为守护线程
                thread.setDaemon(true);
                // 线程优先级为最高
                thread.setPriority(Thread.MAX_PRIORITY);
                return thread;
            }
        });

        // 提交一個任务
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                // executor logic
            }
        });

        /*
         *  但我们一般使用ExecutorService方便我們操作线程池,
         *  通过ExecutorService我们能使用更多的方式操作线程池
         */


        /*
         *  其实通过Executors创建的线程池返回的都是ExecutorService对象,
         *  Executor是ExecutorService的父类
         */
        ExecutorService service = (ExecutorService) threadPool;


        /*
         * 提交一个有返回值(计算结果)的任务 温馨提示,若然任务需要返回计算结果,
         * 使用service.submit(),若然任务不需要
         * 任何返回结果,使用service.execute();
         */
        Future<Integer> future = service.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                return 10;
            }
        });

        /*
         * 当线程池使用完毕可以使用shutdown关闭线程池,
         * shutdown是较为温柔的关闭方式,等待任务执行完毕后才关闭线程池
         * 若然想马上关闭,可以使用service.shutdownNow();
         */
        service.shutdown();

        //更多的API请参考JDK帮助文档
    }
}
