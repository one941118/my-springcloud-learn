package com.ybl.demo.test;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;

import java.io.UnsupportedEncodingException;

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
            syncProducer();
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

}
