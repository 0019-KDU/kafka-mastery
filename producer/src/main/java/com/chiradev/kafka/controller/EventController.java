package com.chiradev.kafka.controller;

import com.chiradev.kafka.dto.Customer;
import com.chiradev.kafka.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @GetMapping("publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message) {
        try{
          for(int i=0;i<=10000;i++){
              kafkaMessagePublisher.sendMessageTopic(message +" : "+ i);
          }
            return ResponseEntity.ok("Message published successfully");
        }catch (Exception ex){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendEvent(@RequestBody Customer customer) {
        try {
            kafkaMessagePublisher.sendEventsTopic(customer);
            return ResponseEntity.ok("Event published successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to publish event: " + e.getMessage());
        }
    }
}
