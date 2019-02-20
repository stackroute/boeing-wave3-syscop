package com.stackroute.processorservice.service;

import com.stackroute.processorservice.model.Metrics;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {


//    @KafkaListener(topics = "Kafka_Example_Test_Final", groupId = "group_id")
//    public void consume(String message) {
//
//        System.out.println(message);
//    }
    @Autowired
    private InfluxServiceImpl influxService;

    @Autowired
    private Metrics metrics;

    @KafkaListener(topics = "Kafka_Example_Test_Final", groupId = "group_id2")
    public void consume(String message) throws JsonProcessingException {
        System.out.println("Consumed msg : " + message);


        String[] strMessage = message.split(",");

        metrics.setContainerId(strMessage[0].split(":")[1].replace("\"",""));
        metrics.setContainerName(strMessage[1].split(":")[1].replace("\"",""));
        metrics.setCpu(strMessage[4].split(":")[1].replace("\"",""));
        metrics.setMem(strMessage[2].split(":")[1].replace("\"",""));
        metrics.setNetIO(strMessage[3].split(":")[1].replace("\"",""));
        metrics.setBlockIO (strMessage[5].split(":")[1].replace("\"",""));
        metrics.setpId (strMessage[6].split(":")[1].replace("\"","").replace("}]"," "));

        System.out.println("Saving Metrics");

        influxService.saveMetrics(metrics);
        System.out.println("Mereics Saved");

        System.out.println(metrics.toString ());

//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        String json = ow.writeValueAsString(strMessage);


    }


}



