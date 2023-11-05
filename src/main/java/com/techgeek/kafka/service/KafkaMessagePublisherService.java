package com.techgeek.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisherService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, Object>> send
                = kafkaTemplate.send("techgeek-demo1", message);
        send.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" + message
                        + "] due to: " + ex.getMessage());
            }

        });
    }
}
