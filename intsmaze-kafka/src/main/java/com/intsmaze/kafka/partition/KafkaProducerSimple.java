package com.intsmaze.kafka.partition;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * @date : 2020/2/5 14:32
 */
public class KafkaProducerSimple {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.19.201:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        /*
         * 指定分区处理类，可选配置，如果不配置，则使用默认的partitioner partitioner.class
         * 默认值：kafka.producer.DefaultPartitioner
         * 用来把消息分到各个partition中，默认行为是对key进行hash。
         */
         props.put("partitioner.class", "com.intsmaze.kafka.partition.TestParirioner");
//        props.put("partitioner.class", "org.apache.kafka.clients.producer.internals.DefaultPartitioner");// 默认值

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 13; i++) {
            Future<RecordMetadata> future = producer.send(new ProducerRecord<String, String>("kafka-test", Integer.toString(i), Integer.toString(i)));
            future.get();
        }
    }
}
