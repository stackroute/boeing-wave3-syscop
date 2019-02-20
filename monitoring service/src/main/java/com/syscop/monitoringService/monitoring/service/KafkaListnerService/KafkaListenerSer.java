package com.syscop.monitoringService.monitoring.service.KafkaListnerService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.syscop.monitoringService.monitoring.service.Model.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerSer {

    @Autowired
    private Monitor monitor;

    @KafkaListener(topics = "Kafka_Example_Test_Final", groupId = "group_id2")
    public void consume(String message) throws JsonProcessingException {
        System.out.println("Consumed msg : " + message);


        String[] strMessage = message.split(",");

        monitor.setContainerId(strMessage[1].split(":")[1].replace("\"",""));
        monitor.setContainerName(strMessage[1].split(":")[1].replace("\"",""));
        monitor.setCpu(strMessage[4].split(":")[1].replace("\"",""));
        monitor.setMem(strMessage[2].split(":")[1].replace("\"",""));
        monitor.setNetIO(strMessage[3].split(":")[1].replace("\"",""));
        monitor.setBlockIO (strMessage[5].split(":")[1].replace("\"",""));
        monitor.setpId (strMessage[6].split(":")[1].replace("\"",""));


       System.out.println(monitor.toString ());

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(strMessage);


    }


}