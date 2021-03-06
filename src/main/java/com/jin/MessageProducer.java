package com.jin;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @description
 * @auth jhj
 * @date 18-1-11 下午2:18
 */
public class MessageProducer {
    public static void main(String[] args){
        Properties props = new Properties();
        props.put("bootstrap.servers", "118.25.42.12:9092;118.25.42.12:9093");
        props.put("partitioner.class", TestPartitioner.class);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            producer.send(new ProducerRecord<String, String>("t1",  null,"msg" + Integer.toString(i)));
        }
        producer.close();
    }


}
