package com.chiradev.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "stream-test",groupId = "cr-group")
    public void consume1(String message) {
        logger.info("Consumed 1 message: " + message);
    }

    @KafkaListener(topics = "stream-test",groupId = "cr-group")
    public void consume2(String message) {
        logger.info("Consumed 2 message: " + message);
    }

    @KafkaListener(topics = "stream-test",groupId = "cr-group")
    public void consume3(String message) {
        logger.info("Consumed 3 message: " + message);
    }

    @KafkaListener(topics = "stream-test",groupId = "cr-group")
    public void consume4(String message) {
        logger.info("Consumed 4 message: " + message);
    }
}
