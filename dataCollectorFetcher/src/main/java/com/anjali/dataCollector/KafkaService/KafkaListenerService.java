package com.anjali.dataCollector.KafkaService;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    @KafkaListener(topics = "kafkaAppRegistration", groupId = "group_id2")
    public void consume(String message) throws JsonProcessingException {
        System.out.println("Consumed msg : " + message);
    }
}
