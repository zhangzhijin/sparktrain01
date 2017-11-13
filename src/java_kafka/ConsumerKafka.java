package java_kafka;
import java.util.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ConsumerKafka {
	
	void recevierMessage(){
		
		  Properties props = new Properties();
		     props.put("bootstrap.servers", "localhost:9092");
		     props.put("group.id", "test");
		     props.put("enable.auto.commit", "true");
		     props.put("auto.commit.interval.ms", "1000");
		     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		     KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		     consumer.subscribe(Arrays.asList("test"));
		     while (true) {
		         ConsumerRecords<String, String> records = consumer.poll(100);
		         for (ConsumerRecord<String, String> record : records)
		             System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
		     }
		
	}

}
