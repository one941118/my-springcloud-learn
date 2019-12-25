package com.ybl.demo.thredademo;

import java.util.concurrent.Callable;

/**
 * @ProjectName: demo
 * @Package: com.ybl.demo.thredademo
 * @ClassName: CallableDemo
 * @Author: liyongbin
 * @Description: ${description}
 * @Date: 2019/12/23 14:25
 * @Version: 1.0
 */
public class CallableDemo implements Callable{

    private String str;

    public CallableDemo(String str){
        this.str = str;
    }


    @Override
    public Object call() throws Exception {
        String aabb = str+"callable线程:"+Thread.currentThread().getName();
        doSomeThing();
        System.out.println(aabb);
        return aabb;
    }

    private void doSomeThing(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
