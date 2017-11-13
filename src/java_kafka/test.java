package java_kafka;

public class test {

	public static void main(String[] args) {
		
		//producer
		 //ProducerHandleKafka producerHandle=new ProducerHandleKafka();
		//Thread producerThread=new Thread(producerHandle);
		//producerThread.run();
		
		ConsumerKafka consumer=new ConsumerKafka();
		consumer.recevierMessage();
	}

}
