package com.stackroute.datacollectorservice.service;

import com.google.gson.*;
import com.stackroute.datacollectorservice.FactoryModel.MetricFactory;
import com.stackroute.datacollectorservice.adapter.UnixEpochDateTypeAdapter;
import com.stackroute.datacollectorservice.model.DataCollectorModel;
import com.stackroute.datacollectorservice.model.ServiceFields;
import com.stackroute.datacollectorservice.model.User;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class KafkaListenerService {

    private KafkaTemplate<String, String> kafkaTemplate;

    private MetricFactory metricFactory;

    private DataCollectorModel dataCollectorModel;

    private ServiceFields dockerService;

    private AppRegDataServiceImpl appRegDataService;
    @Autowired
    public KafkaListenerService(AppRegDataServiceImpl appRegDataService, ServiceFields serviceFields,KafkaTemplate<String, String> kafkaTemplate, MetricFactory metricFactory, DataCollectorModel dataCollectorModel) {
        this.kafkaTemplate = kafkaTemplate;
        this.metricFactory = metricFactory;
        this.dataCollectorModel = dataCollectorModel;
        this.dockerService = serviceFields;
        this.appRegDataService = appRegDataService;
    }



    public KafkaListenerService() {
    }


    @KafkaListener(topics = "kafkaAppRegistration", groupId = "group_id2")
    public void consumeAppRegister(String message) {

        UnixEpochDateTypeAdapter unixEpochDateTypeAdapter = new UnixEpochDateTypeAdapter();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, unixEpochDateTypeAdapter.getUnixEpochDateTypeAdapter())
                .create();


        JsonParser jsonParser = new JsonParser();
        ObjectMapper objMapper = new ObjectMapper();

        Queue<ServiceFields> jobQueue = new LinkedList<>();

        JsonObject obj = (JsonObject) jsonParser.parse(message);


        User user = gson.fromJson(message, User.class);

        appRegDataService.saveUser(user);


        String userName = obj.get("userName").toString();


        JsonArray appArr = obj.getAsJsonArray("applications");

        String appName = appArr.get(0).toString();

        JsonObject appObj = (JsonObject) jsonParser.parse(appName);

        JsonArray serviceArr = appObj.getAsJsonArray("services");

        String appType = appObj.get("applicationType").toString().replace("\"", "");

        if (appType.equals("docker")) {

            ServiceFields dockerService = new ServiceFields();

            dockerService.setUserName(obj.get("userName").toString());
            dockerService.setApplicationIP(appObj.get("ipAddress").toString().replace("\"", ""));
            String agenturl = "http://" + appObj.get("ipAddress").toString().replace("\"", "") + "/docker/stats";
            dockerService.setAgentUrl(agenturl);
            jobQueue.add(dockerService);

        } else {
            for (int i = 0; i < serviceArr.size(); i++) {

                ServiceFields serviceFields = new ServiceFields();
                String services = serviceArr.get(i).toString();

                JsonObject serviceObj = (JsonObject) jsonParser.parse(services);

                serviceFields.setUserName(obj.get("userName").toString());
                serviceFields.setAppplicationType(appObj.get("applicationType").toString().replace("\"", ""));
                serviceFields.setApplicationIP(appObj.get("ipAddress").toString().replace("\"", ""));


                serviceFields.setServiceName(serviceObj.get("serviceName").toString().replace("\"", ""));
                serviceFields.setServiceType(serviceObj.get("serviceType").toString().replace("\"", ""));
                serviceFields.setServicePort(serviceObj.get("portNumber").toString().replace("\"", ""));

                String agenturl = "http://" + appObj.get("ipAddress").toString().replace("\"", "") + "/docker/stats";

                serviceFields.setAgentUrl(agenturl);


                jobQueue.add(serviceFields);

            }
        }

        int jobQueueSize = jobQueue.size();
        Thread threadObj;
        for (int i = 0; i < jobQueueSize; i++) {

            threadObj = new Thread(new ThreadService(jobQueue.remove(), dataCollectorModel, kafkaTemplate, metricFactory));
            threadObj.start();
        }



    }
}



