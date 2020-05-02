package com.intsmaze.kafka.hand;


import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.*;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author ：intsmaze
 * @date ：Created in 2020/2/6 12:16
 * @description： https://www.cnblogs.com/intsmaze/
 * @modified By：
 */
public class HandProducer {

    public static String readFile() {
        InputStream path = HandProducer.class.getResourceAsStream("/data.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(path));
        StringBuffer sbf = new StringBuffer();
        try {
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

    /**
     * @author intsmaze
     * @description: https://www.cnblogs.com/intsmaze/
     *
     * 要求将sendMess成功发送到topic中，且topic的该消息被消费成功
     * @date : 2020/2/5 12:15
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.19.201:9092");
        props.put("max.request.size", "2706174");

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);

        String sendMess="";
//        for(int i=1;i<100;i++)
        {
            sendMess= StringUtils.join(sendMess,HandProducer.readFile());
        }

        // kafka send message
        producer.send(new ProducerRecord<String, String>("test-hand-1", sendMess), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception == null) {
                        System.out.println("success-> offset:" + metadata.offset() + "  partiton:" + metadata.partition());
                    } else {
                        exception.printStackTrace();
                    }
                }
            });
//        producer.send(new ProducerRecord<String, String>("test-hand-1", "123"));

        producer.close();
    }
}
