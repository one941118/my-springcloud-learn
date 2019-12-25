package com.ybl.demo.thredademo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @ProjectName: demo
 * @Package: com.ybl.demo.thredademo
 * @ClassName: RunableDemo
 * @Author: liyongbin
 * @Description: ${description}
 * @Date: 2019/12/23 14:23
 * @Version: 1.0
 */
public class RunnableDemo implements Runnable{

    private String bb;

    public RunnableDemo(String bb){
        this.bb = bb;
    }

    @Override
    public void run() {
        System.out.println(bb+"runnable线程start:"+Thread.currentThread().getName() + "  "+LocalDateTime.now().withNano
            (0));
        doSomeThing();
        System.out.println(bb+"runnable线程end:"+Thread.currentThread().getName() + "  " +LocalDateTime.now().withNano
            (0));

    }

    private void doSomeThing(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
