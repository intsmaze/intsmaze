package com.intsmaze.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author ：intsmaze
 * @date ：Created in 2020/2/5 16:21
 * @description： https://www.cnblogs.com/intsmaze/
 * 1.第一步要设置ack=-1。
 * 2.当ack响应失败要设置retries机制去重发
 * 3.因为重发会导致消息乱序，要设置 max.in.flight.requests.per.connection去保证
 * @modified By：
 */
public class AtLeastOnceProducer {


    @Test
    public void atLeastOnceProducer() throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.19.201:9092");

        props.put("acks", "-1");
        props.put("retries", 3);
        props.put("max.in.flight.requests.per.connection", 1);

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 10; i < 20; i++) {
            Future<RecordMetadata> future = producer.send(new ProducerRecord<String, String>("kafka-test", Integer.toString(i), Integer.toString(i)));
            future.get();
        }
        producer.close();
    }



}
