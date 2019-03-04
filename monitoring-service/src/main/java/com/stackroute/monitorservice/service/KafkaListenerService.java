package com.stackroute.monitorservice.service;

import com.stackroute.monitorservice.model.Monitor;
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService  {


    @Autowired
    private InfluxServiceImpl influxService;

    @Autowired
    private Monitor monitor;

    @KafkaListener(topics = "Kafka_Example_Test_Final", groupId = "group_id")
    public void consume(String message) throws JsonProcessingException {
        System.out.println("Consumed Kafka msg : " + message);



        String[] strMessage = message.split(",");

        monitor.setContainerId(strMessage[0].split(":")[1].replace("\"",""));
        monitor.setContainerName(strMessage[1].split(":")[1].replace("\"",""));
        monitor.setCpu(strMessage[4].split(":")[1].replace("\"",""));
        monitor.setMem(strMessage[2].split(":")[1].replace("\"",""));
        monitor.setNetIO(strMessage[3].split(":")[1].replace("\"",""));
        monitor.setBlockIO (strMessage[5].split(":")[1].replace("\"",""));
        monitor.setpId (strMessage[6].split(":")[1].replace("\"",""));
        monitor.setPort(strMessage[7].split(":")[1].replace("\"","").replace("}]"," "));

        System.out.println("Saving Monitor");

        //to save the monitor to db
        influxService.saveMetrics(monitor);
        System.out.println("Monitor Saved");

        //to convert to  String  method
        System.out.println(monitor.toString ());


    }


}



