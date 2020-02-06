package com.intsmaze.kafka.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author ：intsmaze
 * @date ：Created in 2020/2/5 12:14
 * @description： https://www.cnblogs.com/intsmaze/
 * 一个Kafka客户端，它将记录发送到Kafka集群。
 *
 * <p>生产者是线程安全的，跨线程共享单个生产者实例通常比拥有多个实例要快。
 * <p>
 * 生产者由一个缓冲空间池和一个后台I / O线程组成，该缓冲池保存尚未传输到服务器的记录，该I / O线程负责将这些记录转换为请求并将它们传输到集群。
 * 使用后如果无法关闭生产者，则会泄漏这些资源。
 * @modified By：
 */
public class ModelProducer {

    /**
     * @author intsmaze
     * @description: https://www.cnblogs.com/intsmaze/
     * @date : 2020/2/5 12:15
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.19.201:9092");

        /*
         * request.required.acks,设置发送数据是否需要服务端的反馈,有三个值0,1,-1
         * 0，意味着producer永远不会等待一个来自broker的ack
         * 这个选项提供了最低的延迟，但是持久化的保证是最弱的，当server挂掉的时候会丢失一些数据。
         * 1，意味着在leader replica已经接收到数据后，producer会得到一个ack。
         * 这个选项提供了更好的持久性，因为在server确认请求成功处理后，client才会返回。
         * 如果刚写到leader上，还没来得及复制leader就挂了，那么消息才可能会丢失。
         * -1，意味着在所有的ISR都接收到数据后，producer才得到一个ack。
         * 这个选项提供了最好的持久性，只要还有一个replica存活，那么数据就不会丢失
         */
//        Acks配置控制用于确定请求完成的条件。我们指定的“all”设置将导致记录的完全提交是阻塞，这是最慢但最有保障的设置。
        props.put("acks", "all");

        //如果请求失败，生产者可以自动重试，由于我们将重试指定为0，所以不会重试。启用重试也将导致消息出现重复的可能性。
        props.put("retries", 0);

        //生产者为每个分区维护未发送记录的缓冲区。 这些缓冲区的大小由batch.size配置指定。
        // 增大它可以导致更多的批处理，但是需要更多的内存（因为我们通常会为每个活动分区使用这些缓冲区之一）。
        props.put("batch.size", 16384);

        //默认情况下，即使缓冲区中还有其他未使用的空间，缓冲区也可以立即发送。但是，如果要减少请求数，
        // 可以将linger.ms设置为大于0的值。这将指示生产者在发送请求之前等待该毫秒数，以希望会有更多记录来填充。
        //这类似于TCP中的Nagle算法。
        // 例如，在上面的代码段中，由于我们将延迟时间设置为1毫秒，因此很可能所有100条记录都在一个请求中发送。
        // 但是，如果我们没有将缓冲区填充满，此设置将为我们的请求增加1毫秒的延迟，以等待更多记录到达。
        // 请注意，时间接近的记录，即使linger.ms = 0，这些接近的记录也将一起批处理，因此在高负载下将进行批处理，而与linger配置无关。
        props.put("linger.ms", 1);

        //buffer.memory控制生产者可用于缓冲的内存总量。 如果记录的发送速度超过了将记录发送到服务器的速度，则该缓冲区空间将被耗尽。
        // 当缓冲区空间用尽时，其他发送调用将阻塞。 阻止时间的阈值由max.block.ms确定，此阈值之后将引发TimeoutException。
        props.put("buffer.memory", 33554432);

        //key.serializer和value.serializer指示如何将用户通过其ProducerRecord提供的键和值对象转换为字节。
        // 您可以将随附的ByteArraySerializer或StringSerializer用于简单的字符串或字节类型。
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);


        //不带回调函数的API
//        for (int i = 0; i < 10; i++) {
//            // send（）方法是异步的。调用时，它将记录添加到暂挂记录缓冲区中中并立即返回。这使生产者可以将各个记录一起批处理以提高效率。
//            producer.send(new ProducerRecord<String, String>("test-hand-1", Integer.toString(i), Integer.toString(i)));
//        }

//        //带回调函数的API 异步将记录发送到主题，并在确认发送后调用提供的回调。
//        //发送是异步的，并且一旦记录已存储在等待发送的记录缓冲区中，此方法将立即返回。这允许并行发送许多记录，而不会阻塞等待每个记录之后的响应。
//        for (int i = 0; i < 10; i++) {
//        //完全非阻塞的用法可以利用Callback参数来提供将在请求完成时调用的回调。
//            producer.send(new ProducerRecord<String, String>("test-hand-1", Integer.toString(i), Integer.toString(i)), new Callback() {
//                //回调函数，该方法会在Producer收到ack时调用，为异步调用
//                @Override
//                public void onCompletion(RecordMetadata metadata, Exception exception) {
//        //发送的结果是RecordMetadata，它指定记录发送到的分区，分配的偏移量和记录的时间戳。
//        // 如果主题使用CreateTime，则时间戳将是用户提供的时间戳，如果用户未为记录指定时间戳，则时间戳将是记录的发送时间。
//        // 如果该主题使用LogAppendTime，则时间戳将是附加消息时的Kafka代理本地时间。
//                    if (exception == null) {
//                        System.out.println("success-> offset:" + metadata.offset() + "  partiton:" + metadata.partition());
//                    } else {
//                        exception.printStackTrace();
//                    }
//                }
//            });
//        }


        //同步发送API 必要时等待计算完成，然后检索其结果。
        //由于send调用是异步的，因此它将为RecordMetadata返回Future，该Future将分配给该记录。
        // 在此将来调用get（）将阻塞，直到关联的请求完成，然后返回记录的元数据或引发发送记录时发生的任何异常。
        //如果要模拟一个简单的阻塞调用，可以立即调用get（）方法：
        for (int i = 0; i < 10; i++) {
            RecordMetadata recordMetadata = producer.send(new ProducerRecord<String, String>("test-hand-1", Integer.toString(i), Integer.toString(i))).get();
            System.out.println("success-> offset:" + recordMetadata.offset() + "  partiton:" + recordMetadata.partition());

        }


        producer.close();
    }

}
