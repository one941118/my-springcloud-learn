package com.ybl.demo.test;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;

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
//            syncProducer();
            example();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步消息
     */
    public static void syncProducer() throws Exception {
        //创建一个mqGroup
        DefaultMQProducer producer = new DefaultMQProducer("test_rocket_group");
        //配置mq地址
        producer.setNamesrvAddr("192.168.0.199:9876");
        producer.start();
        Message message = new Message("test_topic", "tags_test", "hello rocket".getBytes(RemotingHelper.DEFAULT_CHARSET));
        //发送消息
        SendResult sendResult = producer.send(message);
        //输出打印发送结果
        System.out.printf("%s%n", sendResult);
        //关掉生产者
        producer.shutdown();
    }

    public static void example ()throws Exception{
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
            DefaultMQProducer("please_rename_unique_group_name");
        // Specify name server addresses.
        producer.setNamesrvAddr("192.168.0.199:9876");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest" /* Topic */,
                "TagA" /* Tag */,
                ("Hello RocketMQ " +
                    i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

}
