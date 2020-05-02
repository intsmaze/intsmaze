package com.intsmaze.kafka.test;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class FlinkProduceOrder extends Thread {
    private String topic;
    private KafkaProducer<String,String> producer;

    String[] urls={"/look","/add","/login","/buy","/rmove","/modify"};

    String[] deviceType={"app","computer","ios","Android"};

    public FlinkProduceOrder(String topic) {
        this.topic = topic;

        Properties properties = new Properties();
        properties.put("bootstrap.servers","192.168.19.131:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(properties);
    }

    @Override
    public void run() {
        Gson gson=new Gson();
        int i=1;
        while (true){
                OrderEvent clickEvent=new OrderEvent(i,urls[1],deviceType[1]);
                String message =  gson.toJson(clickEvent);
                producer.send(new ProducerRecord<String, String>(topic, message));//进kafka的消息是有序的
                System.out.println(message);
                producer.flush();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
    }

    public static void main(String[] args){
        new FlinkProduceOrder("one-partition").start();
    }
}