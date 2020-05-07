package com.intsmaze.kafka.hand;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;


/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * @date : 2020/2/5 14:32
 */
public class HandProducerPartition {

    /**
     * @author intsmaze
     * @description: https://www.cnblogs.com/intsmaze/
     * @date : 2020/2/6 14:26
     *   要求:1.建立一个名为 hand-partition 的topic，分区数设置位3.
     *   2.发送的数据，1-10位于一个分区，11-20位于一个分区
     */
    public static void main(String[] args)  {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.19.201:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("partitioner.class", "");

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 1; i <= 20; i++) {

        }
        producer.close();
    }
}
