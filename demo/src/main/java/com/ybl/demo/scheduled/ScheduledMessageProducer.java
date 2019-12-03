package com.ybl.demo.scheduled;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class ScheduledMessageProducer {
    
     public static void main(String[] args) throws Exception {
         // Instantiate a producer to send scheduled messages
         DefaultMQProducer producer = new DefaultMQProducer("scheduled_group_test");
         //消息发送地址
         producer.setNamesrvAddr("192.168.7.194:9876");
         // Launch producer
         producer.start();
         Message message = new Message("TestTopicScheduled", ("Hello scheduled message").getBytes());
         // This message will be delivered to consumer 10 seconds later.
         message.setDelayTimeLevel(3);
         // Send the message
         System.out.println(System.currentTimeMillis());
         producer.send(message);
         System.out.println(System.currentTimeMillis());
         // Shutdown producer after use.
         producer.shutdown();
     }
        
 }