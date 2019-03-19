package com.stackroute.monitorservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stackroute.monitorservice.model.GraphMetrics;
import com.stackroute.monitorservice.model.Metrics;
import com.stackroute.monitorservice.model.MetricsFinal;
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
    private MetricsFinal metricsFinal;

    @Autowired
    private Metrics metrics;
    
    private String javaMetrics[] = null;


    @KafkaListener(topics = "Kafka_Example_Test_Final3", groupId = "group_id_monitoring")
    public void consume(String message) throws JsonProcessingException, JsonProcessingException {

        JsonParser jsonParser = new JsonParser();
        JsonObject obj = (JsonObject) jsonParser.parse(message);



        JsonObject metricObj = obj.getAsJsonObject("metrics");

        metrics.setBlockIO(metricObj.get("blockIO").toString().replace("\"",""));
        metrics.setContainerId(metricObj.get("containerId").toString().replace("\"",""));
        metrics.setContainerName(metricObj.get("containerName").toString().replace("\"",""));
        metrics.setCpu(Float.parseFloat(metricObj.get("cpu").toString().replace("\"","").replace("%","")));
        metrics.setMem(Float.parseFloat(metricObj.get("mem").toString().replace("\"","").replace("%","")));
        metrics.setNetIO(metricObj.get("netIO").toString().replace("\"",""));
        metrics.setpId(Long.parseLong(metricObj.get("pId").toString().replace("\"","")));

        metricsFinal.setUserName(obj.get("userName").toString().replace("\"",""));
        metricsFinal.setServiceName(obj.get("serviceName").toString().replace("\"",""));
        metricsFinal.setServiceType(obj.get("serviceType").toString().replace("\"",""));
        metricsFinal.setPortNumber(Integer.parseInt(obj.get("portNumber").toString()));
        metricsFinal.setMetrics(metrics);


        String userName = obj.get("userName").toString().replace("\"","");

        //to save the monitor to db
        influxService.saveMetrics(metricsFinal);

        String cpu = metricObj.get("cpu").toString().replace("%","").replace("\"","");
        String mem = metricObj.get("mem").toString().replace("%","").replace("\"","");
        String netIO = metricObj.get("netIO").toString().replace("\"","");;

       
        //for netIO......
       String netio="";
       Double sock2;
         
       if(netIO.contains("kB"))
       {
           String str[]= netIO.split("k");
           netio = str[0];
       }
       else if(netIO.contains("B"))
       {
           String str[]= netIO.split("B");
           sock2 = (Double.valueOf(str[0]))/1000;
           netio = Double.toString(sock2);
       }
        Double sock = Double.valueOf(cpu);
        Double sock1 = Double.valueOf(mem);

        //GraphMetrics object to convert to JSON to send through Socket
        GraphMetrics graphMetrics = new GraphMetrics(
                obj.get("userName").toString().replace("\"",""),
                obj.get("serviceName").toString().replace("\"",""),
                cpu,
                mem,
                netio
        );
        ObjectMapper graphObj = new ObjectMapper();
        String jsonStr = graphObj.writeValueAsString(graphMetrics);


        template.convertAndSend("/topic/graphMetrics/"+userName, jsonStr);


    }


    @KafkaListener(topics = "Kafka_Example_Test_JAVA", groupId = "group_id_monitoring_java")
    public void consumeJava(String message) {
        javaMetrics = message.substring(message.indexOf("[")+1, message.indexOf("]")).split(",");

            for(int i=0; i<javaMetrics.length&&i<2; i++){
                javaMetrics[i] = javaMetrics[i].trim();
                System.out.println(javaMetrics[i]);
                template.convertAndSend("/topic/javaMetric/"+i, javaMetrics[i]);
            }
    }


}



