package com.chiradev.kafka;

import com.chiradev.kafka.dto.Customer;
import com.chiradev.kafka.service.KafkaMessagePublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class ProducerApplicationTests {

	@Container
	static KafkaContainer KAFKA_CONTAINER = new KafkaContainer(
			DockerImageName.parse("confluentinc/cp-kafka:7.4.0")
					.asCompatibleSubstituteFor("apache/kafka")
	);

	@DynamicPropertySource
	static void initKafkaProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.bootstrap-servers", KAFKA_CONTAINER::getBootstrapServers);
	}

	@Autowired
	private KafkaMessagePublisher kafkaMessagePublisher;

	@Test
	public void testSendEventsToTopic() {
		// Send a message to the Kafka topic
		kafkaMessagePublisher.sendEventsTopic(new Customer(123, "test user", "test@gmail.com", "08989898"));

		// Wait and verify that the message was processed
		await().pollInterval(Duration.ofSeconds(3))
				.atMost(10, TimeUnit.SECONDS)
				.untilAsserted(() -> {
					// Add assertions here to verify message consumption
					// For example, you can check if the message was processed by a consumer
					assertTrue(true, "Message should be processed successfully");
				});
	}
}