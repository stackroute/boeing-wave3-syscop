package com.stackroute.monitorservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stackroute.monitorservice.model.GraphMetrics;
import com.stackroute.monitorservice.model.Metrics;
import com.stackroute.monitorservice.model.MetricsFinal;
import com.stackroute.monitorservice.model.Monitor;
//import org.codehaus.jackson.JsonProcessingException;
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


    @KafkaListener(topics = "Kafka_Example_Test_Final3", groupId = "group_id_monitoring")
    public void consume(String message) throws JsonProcessingException, JsonProcessingException {
        System.out.println("Consumed Kafka msg : " + message);


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

        //String[] strMessage = message.split(",");

        String userName = obj.get("userName").toString().replace("\"","");

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
        influxService.saveMetrics(metricsFinal);
        System.out.println("Monitor Saved");

        String cpu = metricObj.get("cpu").toString().replace("%","").replace("\"","");
        String mem = metricObj.get("mem").toString().replace("%","").replace("\"","");
        //String netIO = metricObj.get("netIO").toString().replace("%","").replace("\"","");;

        //String str[] = netIO.split("kB");

        //String netio = str[0];
        //System.out.println("net i o" + netio);
        //double d = Double.parseDouble(cpu);
        Double sock = Double.valueOf(cpu);
        Double sock1 = Double.valueOf(mem);
        //Double sock2 = Double.valueOf(netio);

        System.out.println("cpu======="+cpu);
//        System.out.println("double===="+sock);




        //GraphMetrics object to convert to JSON to send through Socket
        GraphMetrics graphMetrics = new GraphMetrics(
                obj.get("userName").toString().replace("\"",""),
                obj.get("serviceName").toString().replace("\"",""),
                cpu,
                mem,
                metricObj.get("netIO").toString().replace("\"","")
        );

        ObjectMapper graphObj = new ObjectMapper();
        String jsonStr = graphObj.writeValueAsString(graphMetrics);


       
        template.convertAndSend("/topic/graphMetrics/"+userName, "Hello");
        System.out.println("!!@@@@@@" + jsonStr);
        System.out.println(metricsFinal.toString ());


    }


}



