package com.stackroute.dashboardservice.KafkaService;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    //Consumer for App Registration
    @KafkaListener(topics = "kafkaAppRegistration", groupId = "group_id2")
    public void appRegistrationConsume(String message) throws JsonProcessingException {
        System.out.println("Consumed msg : " + message);

    }

    //Consumer for processor service
    @KafkaListener(topics = "kafkaProcessorService", groupId = "group_id2")
    public void processorServiceConsume(String message) throws JsonProcessingException {
        System.out.println("Consumed msg : " + message);

    }

    //Consumer for alert service
    @KafkaListener(topics = "kafkaAlertService", groupId = "group_id2")
    public void alertServiceConsume(String message) throws JsonProcessingException {
        System.out.println("Consumed msg : " + message);

    }

    //consumer for agent service
    @KafkaListener(topics = "kafkaAgentService", groupId = "group_id2")
    public void agentServiceConsume(String message) throws JsonProcessingException {
        System.out.println("Consumed msg : " + message);

    }
}
