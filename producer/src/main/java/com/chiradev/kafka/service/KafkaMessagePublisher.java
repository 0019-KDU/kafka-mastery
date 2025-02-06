package com.chiradev.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, String> template;

    public void  sendMessageTopic(String message){
        CompletableFuture<SendResult<String, String>> future = template.send("chiradev-1", message);
        future.whenComplete((result,ex)->{
            if (ex==null){
                System.out.println("Sent message:[ "+  message + " ] with offset=["+ result.getRecordMetadata().offset() +"]");
            }else {
                System.out.println("Unable to send message=["+ message +"] due to : "+ex.getMessage());
            }
        });
    }
}
