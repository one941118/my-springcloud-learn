package com.ybl.demo.test;

import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * @ProjectName: demo
 * @Package: com.ybl.demo.test
 * @ClassName: RocketDemo
 * @Author: liyongbin
 * @Description: rocketMq 消息生产者
 * @Date: 2019/11/29 14:55
 * @Version: 1.0
 */
public class RocketDemoProducer {

    public static void main(String[] args) {

    }

    /**
     * 同步消息
     */
    public static void SyncProducer(){
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("");
    }
}
