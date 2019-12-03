package com.ybl.demo.test;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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

    @Value("rocket.server.host")
    public static String rocketMqServerIp;

    public static void main(String[] args) {
        try {
            //同步消息
//            syncProducer();
            //异步消息
            asyncProducer();
            //单向传输
//            oneWayProducer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * =========================================================
     * 消息发不出去时一定要注意下版本，官方demo4.3.0貌似有点问题
     * =========================================================
     */
    /**
     * 同步消息
     */
    public static void syncProducer() throws Exception {
        //创建一个mqGroup
        DefaultMQProducer producer = new DefaultMQProducer("test_rocket_group");
        //配置mq地址
        producer.setNamesrvAddr("192.168.7.194:9876");
        producer.start();
        Message message = new Message("test_topic", "tags_test", "hello rocket".getBytes(RemotingHelper.DEFAULT_CHARSET));
        //发送消息
        SendResult sendResult = producer.send(message);
        //输出打印发送结果
        System.out.printf("%s%n", sendResult);
        //关掉生产者
        producer.shutdown();
    }

    /**
     * 异步消息
     */
    public static void asyncProducer() throws Exception {
        //创建一个mqGroup
        DefaultMQProducer producer = new DefaultMQProducer("Jodie_Daily_test");
       //线程计数器 用于确保消息发送后再shutdown producer
        CountDownLatch countDownLatch = new CountDownLatch(1);//由于时用了异步操作，索引send没有等待时间，会导致还没send,就执行了shutdown 消息发送失败
        //配置mq地址
        producer.setNamesrvAddr("192.168.7.194:9876");
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        Message message = new Message("Jodie_topic_10231", "TagA","OrderID188", "hello rocket asy".getBytes
            (RemotingHelper
            .DEFAULT_CHARSET));
        //发送消息
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                countDownLatch.countDown();
                //输出打印发送结果
                System.out.printf("%-10d OK %s %n",
                    1,sendResult.getMsgId());
            }
            @Override
            public void onException(Throwable throwable) {
                System.out.printf("%-10d Exception %s %n", 1,throwable);
                throwable.printStackTrace();
            }
        });
        countDownLatch.await();
        //关掉生产者
        producer.shutdown();
        System.out.println("producer shutdown....");
    }

    /**
     * 异步消息
     */
    public static void oneWayProducer() throws Exception {
        //创建一个mqGroup
        DefaultMQProducer producer = new DefaultMQProducer("test_rocket_group_one_way");
        //配置mq地址
        producer.setNamesrvAddr("192.168.7.194:9876");
        producer.start();
        Message message = new Message("test_topic_one_way", "tags_test_one_way", "hello rocket oneWayProducer"
            .getBytes
            (RemotingHelper
                .DEFAULT_CHARSET));
        //发送消息
         producer.sendOneway(message);
        //关掉生产者
        producer.shutdown();
    }


}
