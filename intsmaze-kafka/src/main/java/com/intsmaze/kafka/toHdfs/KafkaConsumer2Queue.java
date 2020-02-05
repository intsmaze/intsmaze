package com.intsmaze.kafka.toHdfs;


import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

import java.util.concurrent.ArrayBlockingQueue;

public class KafkaConsumer2Queue implements Runnable {
    private String title;
    private KafkaStream<byte[], byte[]> stream;
    private ArrayBlockingQueue queue;

    public KafkaConsumer2Queue(String title, KafkaStream<byte[], byte[]> stream, ArrayBlockingQueue queue) {
        this.title = title;
        this.stream = stream;
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("开始运行 " + title);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        /**
         * 不停地从stream读取新到来的消息，在等待新的消息时，hasNext()会阻塞
         * 如果调用 `ConsumerConnector#shutdown`，那么`hasNext`会返回false
         * */
        while (it.hasNext()) {
            MessageAndMetadata<byte[], byte[]> data = it.next();
            String topic = data.topic();
            int partition = data.partition();
            long offset = data.offset();
            String msg = new String(data.message());
            try {
                queue.put(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format(
                    "Consumer: [%s],  Topic: [%s],  PartitionId: [%d], Offset: [%d], msg: [%s]",
                    title, topic, partition, offset, msg));
        }
        System.err.println(String.format("Consumer: [%s] exiting ...", title));
    }



}
