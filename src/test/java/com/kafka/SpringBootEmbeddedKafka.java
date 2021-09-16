package com.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.event.annotation.BeforeTestClass;

@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public abstract class SpringBootEmbeddedKafka {

	@Autowired
	public KafkaTemplate<String, String> template;

	@Autowired
	public EmbeddedKafkaBroker kafkaEmbedded;

	public static EmbeddedKafkaBroker embeddedKafka = new EmbeddedKafkaBroker(1, true, 0);

	@BeforeTestClass
	public static void setUpClass() {
		System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getBrokersAsString());
		System.setProperty("spring.cloud.stream.kafka.binder.zkNodes", embeddedKafka.getZookeeperConnectionString());
	}

}
