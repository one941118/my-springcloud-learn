package com.ybl.demo.batchmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: demo
 * @Package: com.ybl.demo.test
 * @ClassName: RocketDemo
 * @Author: liyongbin
 * @Description: rocketMq 消息生产者
 * @Date: 2019/11/29 14:55
 * @Version: 1.0
 */
public class RocketBatchProducer {

    @Value("rocket.server.host")
    public static String rocketMqServerIp;

    public static void main(String[] args) {
        try {
            //同步消息
            syncProducer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     */
    /**
     * 同步消息
     */
    public static void syncProducer() throws Exception {
        //创建一个mqGroup
        DefaultMQProducer producer = new DefaultMQProducer("batch_test_rocket_group");
        //配置mq地址
        producer.setNamesrvAddr("192.168.7.194:9876");
        producer.start();
        List<Message> messages = new ArrayList<>();
        Message message01 = new Message("batch_test_topic", "batch_tags_test", "hello rocket batch01".getBytes
            (RemotingHelper
            .DEFAULT_CHARSET));
        messages.add(message01);
        Message message02 = new Message("batch_test_topic", "batch_tags_test", "hello rocket batch02".getBytes
            (RemotingHelper
            .DEFAULT_CHARSET));
        messages.add(message02);
        Message message03 = new Message("batch_test_topic", "batch_tags_test", "hello rocket batch03".getBytes
            (RemotingHelper
            .DEFAULT_CHARSET));
        messages.add(message03);
        Message message04 = new Message("batch_test_topic", "batch_tags_test", "hello rocket batch04".getBytes
            (RemotingHelper
                .DEFAULT_CHARSET));
        messages.add(message04);
        //发送消息
        ListSplitter listSplitter = new ListSplitter(messages);
        while (listSplitter.hasNext()){
            SendResult sendResult = producer.send(listSplitter.next());
            //输出打印发送结果
            System.out.printf("%s%n", sendResult);
            boolean aabb = "".equals("aabb");
        }
        //关掉生产者
        producer.shutdown();
    }
}

    class ListSplitter implements Iterator<List<Message>> {
        private int sizeLimit = 1000 * 1000;
        private final List<Message> messages;
        private int currIndex;

      public ListSplitter(List<Message> messages){
            this.messages = messages;
        }
        @Override
        public List<Message> next() {
            int nextIndex = currIndex;
            int totalSize = 0;
            for (; nextIndex < messages.size(); nextIndex++) {
                Message message = messages.get(nextIndex);
                int tmpSize = message.getTopic().length() + message.getBody().length;
                Map<String, String> properties = message.getProperties();
                for (Map.Entry<String, String> entry : properties.entrySet()) {
                    tmpSize += entry.getKey().length() + entry.getValue().length();
                }
                tmpSize = tmpSize + 20; //for log overhead
                if (tmpSize > sizeLimit) {
                    //it is unexpected that single message exceeds the sizeLimit
                    //here just let it go, otherwise it will block the splitting process
                    if (nextIndex - currIndex == 0) {
                        //if the next sublist has no element, add this one and then break, otherwise just break
                        nextIndex++;
                    }
                    break;
                }
                if (tmpSize + totalSize > sizeLimit) {
                    break;
                } else {
                    totalSize += tmpSize;
                }
            }
            List<Message> subList = messages.subList(currIndex, nextIndex);
            currIndex = nextIndex;
            return subList;
        }

        @Override
        public boolean hasNext() {
            return messages.size() > currIndex;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not allowed to remove");
        }
    }

