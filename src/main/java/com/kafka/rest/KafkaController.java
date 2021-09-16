package com.kafka.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.producer.KafkaProducer;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class KafkaController {

	private final KafkaProducer kafkaProducer;

	  @Value("${kafka.topic}")
	private String topic;

	@GetMapping("/messages/send/{message}")
	public ResponseEntity<String> sendMessage(@PathVariable("message") String message) {
		kafkaProducer.sendMessage(topic, message);
		return ResponseEntity.ok(message);
	}
}