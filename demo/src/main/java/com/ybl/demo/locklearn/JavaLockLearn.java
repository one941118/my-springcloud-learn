package com.ybl.demo.locklearn;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.tomcat.jni.Lock;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: liyongbin
 * @date: 2020/4/25 15:10
 * @version: 1.0
 */
public class JavaLockLearn {

    static ReentrantLock localLock = new ReentrantLock();

    private static final int num = 1000;

    static Integer stockNum = 1001;

    public static void main(String[] args) throws InterruptedException {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(stockNum, stockNum, 10L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
                namedThreadFactory,new ThreadPoolExecutor.AbortPolicy());
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    localLock.lock();
                    stockNum--;
                    countDownLatch.countDown();
                    localLock.unlock();
                }
            });
        }
        countDownLatch.await();
        threadPoolExecutor.shutdown();
        System.out.println( "stockNum" + stockNum);
    }

}
