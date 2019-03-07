package com.stackroute.monitorservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stackroute.monitorservice.model.Metrics;
import com.stackroute.monitorservice.model.MetricsFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService  {


    int counter = 0;
    private InfluxServiceImpl influxService;

    private MetricsFinal metricsFinal;

    private Metrics metrics;

    private SimpMessagingTemplate template;

    @Autowired
    public KafkaListenerService(InfluxServiceImpl influxService, MetricsFinal metricsFinal, Metrics metrics, SimpMessagingTemplate template) {
        this.influxService = influxService;
        this.metricsFinal = metricsFinal;
        this.metrics = metrics;
        this.template = template;
    }

    @KafkaListener(topics = "Kafka_Example_Test_Final3", groupId = "group_id_monitoring")
    public void consume(String message) throws JsonProcessingException {
        System.out.println("Consumed msg : " + message);

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



        counter++;
        System.out.println("Messages received "+counter);




        influxService.saveMetricsFinal(metricsFinal);
//            System.out.println("Metrics Saved");


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
//        System.out.println(monitor.toString ());



        System.out.println(metricsFinal.toString ());


    }


}



