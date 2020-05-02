package com.intsmaze.kafka.test;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;


/**
 * 该类仅仅模拟 flinkKafka在kafkaproducer后面30秒启动后，接收这30秒内的数据也会根据数据的事件事件归并窗口，
 * 这里消息其实是有序的，只是30秒的消息会集中一秒内发送到flink,flink必须根据eventtime来区分，不然统计的指标就会突然很抖。
 * 关于消息乱序延迟到到，还请看FlinkProduceUnderOrder生成者
 */
public class FlinkProduceEvent extends Thread {
    private String topic;
    private KafkaProducer<String, String> producer;

    public FlinkProduceEvent(String topic) {
        this.topic = topic;

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.19.131:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(properties);
    }

    @Override
    public void run() {
        Gson gson = new Gson();

        while (true) {
            EventBean clickEvent = new EventBean(1, System.currentTimeMillis());
            String message = gson.toJson(clickEvent);
            producer.send(new ProducerRecord<String, String>(topic, message));
            System.out.println(message);
            producer.flush();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        new FlinkProduceEvent("one-partition").start();
    }
}