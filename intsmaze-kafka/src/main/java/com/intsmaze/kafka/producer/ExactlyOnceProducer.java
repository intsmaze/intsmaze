package com.intsmaze.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author ：intsmaze
 * @date ：Created in 2020/2/5 16:21
 * @description： https://www.cnblogs.com/intsmaze/
 * 从Kafka 0.11开始，KafkaProducer支持两种附加模式：幂等生产者和事务生产者。
 * @modified By：
 */
public class ExactlyOnceProducer {

    /**
     * @author intsmaze
     * @description: https://www.cnblogs.com/intsmaze/
     * 幂等的生产者将Kafka的交付语义从at least once增强到exactly once。特别是，生产者重试将不再引入重复项。事务生产方允许应用程序原子地将消息发送到多个分区（和主题）。
     * 要启用幂等，必须在生产者端将enable.idempotence配置设置为true。如果设置，则重试配置将默认为Integer.MAX_VALUE，而acks配置将默认为all。
     * 幂等生产者没有API更改，因此无需修改现有应用程序即可利用此功能。
     * <p>
     * 为了利用幂等的生产者，必须避免应用程序级别的重新发送，因为这些应用程序无法重复删除。（就是不要利用spring retry等框架的重拾机制，消息发送失败，在去调用kafka发送消息的api）
     * 因此，如果生产者启用幂等性，建议将重试配置保留为未设置状态，因为它将默认为Integer.MAX_VALUE。
     * 最后，生产者只能保证在单个会话中发送的消息具有幂等性。
     * @date : 2020/2/5 16:06
     */
    @Test
    public void idempotence() throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.19.201:9092");

        //当设置为“ true”时，生产者将确保每个消息的确切副本被写入流中。 如果为“ false”，则生产者由于代理失败等原因而重试，可能会在流中写入重试消息的副本。
        // 请注意，启用幂等性要求max.in.flight.requests.per.connection小于或等于5，重试大于0，ack必须为“ all”。
        // 如果用户未明确设置这些值，则将选择合适的值。 如果设置了不兼容的值，则将引发ConfigException。
        props.put("enable.idempotence", true);

//        客户端在阻塞之前将在单个连接上发送的未确认请求的最大数量。 请注意，如果将此设置设置为大于1且发送失败，则存在由于重试而导致消息重新排序的风险（即，如果启用了重试）。
        props.put("max.in.flight.requests.per.connection", 1);//默认5

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 10; i < 20; i++) {
            Future<RecordMetadata> future = producer.send(new ProducerRecord<String, String>("test-hand-1", Integer.toString(i), Integer.toString(i)));
            future.get();
        }
        producer.close();
    }


    /**
     * @author intsmaze
     * @description: https://www.cnblogs.com/intsmaze/
     * <p>
     * 要使用事务生产者和附带的API，必须设置transactional.id 配置属性。如果设置了transactional.id，则会自动启用幂等性以及幂等性依赖的生产者配置。
     * 此外，事务中包含的主题应配置为有保障的。特别是，replication.factor（borker配置）应该至少为3，并且这些主题的min.insync.replicas（borker配置，默认为1）应该设置为2。
     * 最后，为了实现端到端的事务保证，在kafak的消费者端必须配置为仅读取已提交的消息。
     *
     * min.insync.replicas 的意义:
     * 当生产者将acks设置为“ all”（或“ -1”）时，min.insync.replicas指定必须确认写入才能使写入成功的最小副本数。 如果无法满足此最小值，则生产者将引发异常（NotEnoughReplicas或NotEnoughReplicasAfterAppend）。
     * 一起使用时，min.insync.replicas和acks可使您实施更大的耐用性保证。 典型的情况是创建一个复制因子为3的主题，将min.insync.replicas设置为2，并产生“ all”。 如果大多数副本未收到写入，这将确保生产者引发异常。
     *
     * <p>
     * transactional.id的目的是在单个生产者实例上与多个会话之间的事务恢复。它通常从分区的有状态应用程序中的分片标识符派生。
     * 因此，它对于在分区应用程序中运行的每个生产者实例应该是唯一的。
     * <p>
     * 所有事务性API都是阻塞式的，并在失败时引发异常。
     * @date : 2020/2/5 16:25
     */
    @Test
    public void transactions()  {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.19.201:9092");
        props.put("transactional.id", "intsmaze-transactional-id");
        Producer<String, String> producer = new KafkaProducer<>(props, new StringSerializer(), new StringSerializer());

        /**
         * 如示例所示，每个生产者只能有一个未完成的事务。 在beginTransaction（）和commitTransaction（）调用之间发送的所有消息将成为单个事务的一部分。
         * 指定transactional.id时，生产者发送的所有消息都必须是事务的一部分。
         */
        producer.initTransactions();
        try {
            producer.beginTransaction();
            for (int i = 1000; i < 1010; i++) {
                //事务性生产者使用异常来传达错误状态。 不需要为producer.send（）指定回调或在返回的Future上调用.get（）：
                // 如果任何producer.send（）或事务期间中的事务调用遇到不可恢复的错误，则将引发KafkaException。 有关从事务性发送中检测错误的更多详细信息，请参见send（ProducerRecord）文档。
                producer.send(new ProducerRecord<>("kafka-test", Integer.toString(i), Integer.toString(i)));
            }
            producer.commitTransaction();
        } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
            // We can't recover from these exceptions, so our only option is to close the producer and exit.
            producer.close();
        } catch (KafkaException e) {
            // For all other exceptions, just abort the transaction and try again.
            //通过在接收到KafkaException之后调用producer.abortTransaction（），我们可以确保将任何成功的写入都标记为已中止，从而保留事务保证。
            //该客户端可以与0.10.0或更高版本的代理进行通信。
            producer.abortTransaction();
        }
        producer.close();
    }

}
