package com.ybl.demo.test;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
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
    public static  String rocketMqServerIp;

    public static void main(String[] args) {

    }

    /**
     * 同步消息
     */
    public static void SyncProducer() throws Exception {
        //创建一个mqGroup
        DefaultMQProducer producer = new DefaultMQProducer("test_rocket_group");
        //配置mq地址
        producer.setNamesrvAddr(rocketMqServerIp);
        producer.start();
        Message message = new Message("test_topic", "tags_test", "hello rocket".getBytes());
        //发送消息
        SendResult sendResult = producer.send(message);
        //输出打印发送结果
        System.out.printf("%s%n", sendResult);
        //关掉生产者
        producer.shutdown();
    }

}
