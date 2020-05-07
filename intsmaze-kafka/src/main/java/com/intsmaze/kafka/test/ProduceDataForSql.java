package com.intsmaze.kafka.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

public class ProduceDataForSql extends Thread {

    static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss SSS").create();

    private KafkaProducer<String, String> producer;

    public ProduceDataForSql() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.19.201:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("request.required.acks", "1");

        producer = new KafkaProducer<String, String>(props);
    }

    @Override
    public void run() {

        int seqId = 100;
        for (int i = 0; i < 1000; i++) {
            SourceData sourceData = new SourceData();
            sourceData.setStepType("充电");
            sourceData.setSeqId(seqId);
            seqId = seqId + 1;
            sourceData.setStepNumber(i);
            sourceData.setTestTime(new Timestamp(new Date().getTime()));
            sourceData.setReceiveTime(new Timestamp(new Date().getTime()));
            sourceData.setUuid(UUID.randomUUID().toString()+"--"+seqId);
            sourceData.setCycleNumber(1);
            sourceData.setFlowId("19920829");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("catl-trigger-test-1",
                    sourceData.getFlowId(), gson.toJson(sourceData));
            producer.send(producerRecord);
        }
        producer.close();
    }


    public static void main(String[] args) throws InterruptedException {
        new ProduceDataForSql().start();
    }
}
