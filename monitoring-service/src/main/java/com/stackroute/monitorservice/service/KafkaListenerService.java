package com.stackroute.monitorservice.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stackroute.monitorservice.model.Monitor;
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService  {


    @Autowired
    SimpMessagingTemplate template;

    @Autowired
    private InfluxServiceImpl influxService;

    @Autowired
    private Monitor monitor;

    @KafkaListener(topics = "Kafka_Example_Test_Final2", groupId = "group_id_monitoring")
    public void consume(String message) throws JsonProcessingException {
        System.out.println("Consumed Kafka msg : " + message);


        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(message);



        JsonObject metricObj = jsonObject.getAsJsonObject("metrics");

        monitor.setContainerId(metricObj.get("containerId").toString());
        monitor.setContainerName(metricObj.get("containerName").toString());
        monitor.setCpu(metricObj.get("cpu").toString());
        monitor.setMem(metricObj.get("mem").toString());
        monitor.setNetIO(metricObj.get("netIO").toString());
        monitor.setBlockIO (metricObj.get("blockIO").toString());
        monitor.setpId (metricObj.get("pId").toString());
        monitor.setPort(jsonObject.get("portNumber").toString());


        //String[] strMessage = message.split(",");




//        monitor.setContainerId(strMessage[0].split(":")[1].replace("\"",""));
//        monitor.setContainerName(strMessage[1].split(":")[1].replace("\"",""));
//        monitor.setCpu(strMessage[4].split(":")[1].replace("\"",""));
//        monitor.setMem(strMessage[2].split(":")[1].replace("\"",""));
//        monitor.setNetIO(strMessage[3].split(":")[1].replace("\"",""));
//        monitor.setBlockIO (strMessage[5].split(":")[1].replace("\"",""));
//        monitor.setpId (strMessage[6].split(":")[1].replace("\"",""));
//        monitor.setPort(strMessage[7].split(":")[1].replace("\"","").replace("}]"," "));

        System.out.println("Saving Monitor");

        //to save the monitor to db
        influxService.saveMetrics(monitor);
        System.out.println("Monitor Saved");

        String cpu = metricObj.get("cpu").toString().replace("%","").replace("\"","");
        String mem = metricObj.get("mem").toString().replace("%","").replace("\"","");
        String netIO = metricObj.get("netIO").toString().replace("%","").replace("\"","");;

        String str[] = netIO.split("kB");

        String netio = str[0];
        System.out.println("net i o" + netio);
        //double d = Double.parseDouble(cpu);
        Double sock = Double.valueOf(cpu);
        Double sock1 = Double.valueOf(mem);
        Double sock2 = Double.valueOf(netio);

        System.out.println("cpu======="+cpu);
//        System.out.println("double===="+sock);
//
////
        template.convertAndSend("/topic/cpu-metrics",cpu);
        template.convertAndSend("/topic/mem-metrics",sock1);
        template.convertAndSend("/topic/netIO-metrics",sock2);

        //to convert to  String  method
        System.out.println(monitor.toString ());


    }


}



