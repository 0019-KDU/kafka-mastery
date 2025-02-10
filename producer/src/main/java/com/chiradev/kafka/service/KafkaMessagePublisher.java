package com.chiradev.kafka.service;

import com.chiradev.kafka.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void  sendMessageTopic(String message){
        CompletableFuture<SendResult<String, Object>> future = template.send("stream-test", message);
        future.whenComplete((result,ex)->{
            if (ex==null){
                System.out.println("Sent message:[ "+  message + " ] with offset=["+ result.getRecordMetadata().offset() +"]");
            }else {
                System.out.println("Unable to send message=["+ message +"] due to : "+ex.getMessage());
            }
        });
    }


    public void sendEventsTopic(Customer customer) {
        try {
            // Send the Customer object directly (no toString()!)
            CompletableFuture<SendResult<String, Object>> future = template.send("customer-topic", customer);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message:[ " + customer + " ] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" + customer + "] due to: " + ex.getMessage());
                }
            });
        } catch (Exception e) {
            System.out.println("Exception occurred while sending message: " + e.getMessage());
        }
    }
}


