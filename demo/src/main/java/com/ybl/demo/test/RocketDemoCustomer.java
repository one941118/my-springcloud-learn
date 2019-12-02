package com.ybl.demo.test;

import java.util.List;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @ProjectName: demo
 * @Package: com.ybl.demo.test
 * @ClassName: RocketDemoCustomer
 * @Author: liyongbin
 * @Description: rocket消费
 * @Date: 2019/11/29 17:58
 * @Version: 1.0
 */
public class RocketDemoCustomer {


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
        consumer.subscribe("Jodie_topic_10231","*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                for (MessageExt msg : msgs) {
                    System.out.println(new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }

}
