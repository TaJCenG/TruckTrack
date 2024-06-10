package com.taj.kafka.producer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


public class kafkaproducer {
	public static void main (String [] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.LongDeserializer");
		props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.setProperty("group.id", "TrackGroup");
		KafkaConsumer<Long, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList("TruckTopic"));
		
		ConsumerRecords<Long, String> orders = consumer.poll(Duration.ofSeconds(10));
		orders.forEach(tracking -> {
			List<String> coordinates = Arrays.asList(tracking.value().split(","));
			System.out.println("Truck " + tracking.key());
			System.out.println("Latitude " + coordinates.get(0));
			System.out.println("Longitude " + coordinates.get(1));
			System.out.println("Latitude {}::Longitude" + tracking.value());
			System.out.println("Truck ID: " + tracking.key() + ", Latitude: " + coordinates.get(0) + ", Longitude: " + coordinates.get(1));
		});
consumer.close();
	}

}
