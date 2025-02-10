package com.chiradev.kafka.consumer;

import com.chiradev.kafka.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "customer-topic", groupId = "cr-group")
    public void consume1( Customer customer) {
        logger.info("Consumed 1 message: " + customer.toString());
    }

//    @KafkaListener(topics = "stream-test",groupId = "cr-group")
//    public void consume2(String message) {
//        logger.info("Consumed 2 message: " + message);
//    }
//
//    @KafkaListener(topics = "stream-test",groupId = "cr-group")
//    public void consume3(String message) {
//        logger.info("Consumed 3 message: " + message);
//    }
//
//    @KafkaListener(topics = "stream-test",groupId = "cr-group")
//    public void consume4(String message) {
//        logger.info("Consumed 4 message: " + message);
//    }
}
