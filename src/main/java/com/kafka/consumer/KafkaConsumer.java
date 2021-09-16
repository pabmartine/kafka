package com.kafka.consumer;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaConsumer {

	private CountDownLatch latch = new CountDownLatch(1);

	@KafkaListener(topics = "${kafka.topic}")
	public void listener(String message) {
		log.info("Message received in #1 {} ", message);
		latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}