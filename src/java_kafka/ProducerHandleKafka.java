package java_kafka;

import java.util.*;

public class ProducerHandleKafka implements Runnable {

	ProducerKafka producerKafka = new ProducerKafka();
	private String topic = "test";
	private String messageKey;
	private String messageValue;

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		int i = 0;
		while (true) {
			if (i % 10 == 0) {

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				messageKey = "messageKey" + i;
				messageValue = "messageValue" + i;
				producerKafka.sentMessage(topic, messageKey, messageValue);
			}

			i++;
		}

	}

}
