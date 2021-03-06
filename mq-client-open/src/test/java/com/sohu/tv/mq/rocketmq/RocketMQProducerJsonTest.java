package com.sohu.tv.mq.rocketmq;

import java.util.HashMap;
import java.util.Map;

import org.apache.rocketmq.client.producer.SendResult;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.sohu.index.tv.mq.common.Result;

public class RocketMQProducerJsonTest {

    private RocketMQProducer producer;

    @Before
    public void init() {
        producer = TestUtil.buildProducer("mqcloud-test-topic-producer", "mqcloud-test-topic");
        producer.start();
    }

    @Test
    public void produce() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", "b");
        map.put("c", "d");
        map.put("o", "c");
        String str = JSON.toJSONString(map);
        Result<SendResult> sendResult = producer.publish(str, "abc");
        System.out.println(sendResult);
        Assert.assertTrue(sendResult.isSuccess());
    }
    
    @After
    public void clean() {
        producer.shutdown();
    }
}
