package com.intsmaze.kafka.test;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class FlinkProduceUnderOrder extends Thread {
    private String topic;
    private KafkaProducer<String, String> producer;

    public FlinkProduceUnderOrder(String topic) {
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
            List<String> list = new LinkedList<String>();
            for (int i = 0; i < 9; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                EventBean clickEvent = new EventBean(1, System.currentTimeMillis());
                String message = gson.toJson(clickEvent);
                list.add(message);
            }
            List<String> listUnderOrder = new LinkedList<String>();
            listUnderOrder.add(list.get(0));
            listUnderOrder.add(list.get(1));

            listUnderOrder.add(list.get(3));
            listUnderOrder.add(list.get(4));

            listUnderOrder.add(list.get(6));
            listUnderOrder.add(list.get(7));


            for (int i = 0; i < 6; i++) {
                producer.send(new ProducerRecord<String, String>(topic, list.get(i)));
                System.out.println(list.get(i));
                producer.flush();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
        }
//            producer.send(new ProducerRecord<String, String>(topic, list.get(i)));
//            producer.flush();
    }

    public static void main(String[] args) {
        new FlinkProduceUnderOrder("one-partition").start();
    }
}