package com.ybl.demo.thredademo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ProjectName: demo
 * @Package: com.ybl.demo.thredademo
 * @ClassName: ThreadPoolDemo
 * @Author: liyongbin
 * @Description: ${description}
 * @Date: 2019/12/23 14:27
 * @Version: 1.0
 */
public class ThreadPoolDemo {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) throws Exception {
        poolCallableTest();
//        poolRunnableTest();
    }


    public static void poolCallableTest(){
        //通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(QUEUE_CAPACITY),
            new ThreadPoolExecutor.CallerRunsPolicy());
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //创建WorkerThread对象（WorkerThread类实现了Runnable 接口）
            CallableDemo worker = new CallableDemo("" + i);
            //执行Runnable
            Future submit = executor.submit(worker);
            list.add(submit);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
        for (Future aa : list){
            try {
                System.out.println(aa.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }



    public static void poolRunnableTest(){
        //通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(QUEUE_CAPACITY),
            new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 100; i++) {
            //创建WorkerThread对象（WorkerThread类实现了Runnable 接口）
            RunnableDemo worker = new RunnableDemo("" + i);
            //执行Runnable
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

}
