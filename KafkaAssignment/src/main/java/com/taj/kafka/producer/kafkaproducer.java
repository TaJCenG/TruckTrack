package com.taj.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class kafkaproducer {
	public static void main (String [] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.serializer", "org.apache.kafka.common.serialization.LongSerializer");
		props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		
		try (KafkaProducer<Long, String> producer = new KafkaProducer<>(props)) {
			//ProducerRecord<Long, String> record = new ProducerRecord<>("TruckTopic",01L, "22.5726N,88.3639E");
			//producer.send(record, new TrackCallback());
			producer.send(new ProducerRecord<Long, String>("TruckTopic",01L,"41.72556N,49.94694W"));
            producer.send(new ProducerRecord<Long, String>("TruckTopic", 02L,"37.4205N,24.1702E"));
            producer.send(new ProducerRecord<Long, String>("TruckTopic",03L, "49.25N,10.25W"));
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
