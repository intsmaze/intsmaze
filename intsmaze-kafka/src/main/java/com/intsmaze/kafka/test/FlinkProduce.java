package com.intsmaze.kafka.test;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class FlinkProduce extends Thread {
    private String topic;
    private KafkaProducer<String, String> producer;

    String[] urls = {"/look", "/add", "/login", "/buy", "/rmove", "/modify"};

    String[] deviceType = {"app", "computer", "ios", "Android"};

    public FlinkProduce(String topic) {
        this.topic = topic;

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.19.131:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("partitioner.class", "com.intsmaze.kafka.partition.TestPartitioner");
        producer = new KafkaProducer<String, String>(properties);
    }

    @Override
    public void run() {
        Gson gson = new Gson();
        while (true) {

            List<String> list = new LinkedList<String>();

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {

                }
                ClickEvent clickEvent = new ClickEvent(new Random().nextInt(1), urls[new Random().nextInt(6)], System.currentTimeMillis(), deviceType[new Random().nextInt(4)]);
                String message = gson.toJson(clickEvent);
                list.add(message);
            }
            for (int i = 0; i < 10; i++) {
                int num = new Random().nextInt(10);
                producer.send(new ProducerRecord<String, String>(topic, list.get(num)));//进kafka的消息是乱序的
                System.out.println(list.get(num));
                producer.flush();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new FlinkProduce("test").start();
    }
}