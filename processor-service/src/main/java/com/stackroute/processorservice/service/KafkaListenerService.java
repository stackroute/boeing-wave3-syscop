package com.stackroute.processorservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stackroute.processorservice.model.Metrics;
import com.stackroute.processorservice.model.MetricsFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaListenerService  {


    int counter = 0;
    @Autowired
    private InfluxServiceImpl influxService;

    @Autowired
    private MetricsFinal metricsFinal;

    @Autowired
    private  Metrics metrics;



    @KafkaListener(topics = "Kafka_Example_Test_Final3", groupId = "group_id")
    public void consume(String message) throws JsonProcessingException {

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
            influxService.saveMetricsFinal(metricsFinal);

    }


}



