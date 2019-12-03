package com.ybl.demo.broadcast;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @ProjectName: demo
 * @Package: com.ybl.demo.test
 * @ClassName: RocketDemoCustomer
 * @Author: liyongbin
 * @Description: rocket消费
 * @Date: 2019/11/29 17:58
 * @Version: 1.0
 */
public class RocketDemoCustomer02 {


    public static void main(String[] args) {
        try {
            customer();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public static void customer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Jodie_Daily_test");
        consumer.setNamesrvAddr("192.168.7.194:9876");
        consumer.subscribe("Jodie_topic_10231","TagA");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setMessageModel(MessageModel.BROADCASTING);//设置消费者为广播模式
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                System.out.println(Thread.currentThread().getName() + " Receive New Messages CUSTOMER02: " + msgs +
                    "%n");
                for (MessageExt msg : msgs) {
                    System.out.println(new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.printf("broadcast Consumer Started.%n");
    }

}
