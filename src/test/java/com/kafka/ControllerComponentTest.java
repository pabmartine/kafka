package com.kafka;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.kafka.consumer.KafkaConsumer;
import com.kafka.producer.KafkaProducer;

@SpringBootTest
@ActiveProfiles("test")
public class ControllerComponentTest extends SpringBootEmbeddedKafka {

	@Autowired
	private KafkaConsumer consumer;

	@Autowired
	private KafkaProducer producer;

	@Value("${kafka.topic}")
	private String topic;

	@Test
	public void testSendAndReceive() throws Exception {
		producer.sendMessage(topic, "Sending test message");

		consumer.getLatch()
				.await(10000, TimeUnit.MILLISECONDS);

		assertThat(consumer.getLatch()
				.getCount(), equalTo(0L));
	}
}