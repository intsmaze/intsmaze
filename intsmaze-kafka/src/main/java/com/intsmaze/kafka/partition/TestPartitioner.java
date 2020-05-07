package com.intsmaze.kafka.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class TestPartitioner implements Partitioner {

    private final AtomicInteger counter = new AtomicInteger(new Random().nextInt());

    /**
     * A cheap way to deterministically convert a number to a positive value. When the input is
     * positive, the original value is returned. When the input number is negative, the returned
     * positive value is the original value bit AND against 0x7fffffff which is not its absolutely
     * value.
     * <p>
     * Note: changing this method in the future will possibly cause partition selection not to be
     * compatible with the existing messages already placed on a partition.
     *
     * @param number a given number
     * @return a positive number.
     */
    private static int toPositive(int number) {
        return number & 0x7fffffff;
    }

    /**
     * Compute the partition for the given record.
     *
     * @param topic      The topic name
     * @param key        The key to partition on (or null if no key)
     * @param keyBytes   serialized key to partition on (or null if no key)
     * @param value      The value to partition on or null
     * @param valueBytes serialized value to partition on or null
     * @param cluster    The current cluster metadata
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();
        if (keyBytes == null) {
            int nextValue = counter.getAndIncrement();
            List<PartitionInfo> availablePartitions = cluster.availablePartitionsForTopic(topic);
            if (availablePartitions.size() > 0) {
                int part = TestPartitioner.toPositive(nextValue) % availablePartitions.size();
                int num = availablePartitions.get(part).partition();
                System.out.println(num + "=============");
                return num;
            } else {
                // no partitions are available, give a non-available partition
                int num = TestPartitioner.toPositive(nextValue) % numPartitions;
                System.out.println(num + "-----------");
                return num;
            }
        } else {
            // hash the keyBytes to choose a partition
            return TestPartitioner.toPositive(Utils.murmur2(keyBytes)) % numPartitions;
        }
    }

    @Override
    public void close() {

    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println((Utils.murmur2("123".getBytes()) & 0x7fffffff) % 3);
        }
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
