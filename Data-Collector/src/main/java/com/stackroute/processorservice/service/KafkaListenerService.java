package com.stackroute.processorservice.service;

import com.google.gson.*;
import com.stackroute.processorservice.FactoryModel.MetricFactory;
import com.stackroute.processorservice.adapter.UnixEpochDateTypeAdapter;
import com.stackroute.processorservice.model.Application;
import com.stackroute.processorservice.model.DataCollectorModel;
import com.stackroute.processorservice.model.ServiceFields;
import com.stackroute.processorservice.model.User;
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


        System.out.println("inside the kafka listener");
        JsonParser jsonParser = new JsonParser();
        ObjectMapper objMapper = new ObjectMapper();

        Queue<ServiceFields> jobQueue = new LinkedList<>();

        JsonObject obj = (JsonObject) jsonParser.parse(message);

        System.out.println("Consumed msg : " + message);

        User user = gson.fromJson(message, User.class);

        System.out.println("Save User");
        appRegDataService.saveUser(user);


        String userName = obj.get("userName").toString();

        //System.out.println("##$$" + userName);
        System.out.println("@@@!!" + obj);

        JsonArray appArr = obj.getAsJsonArray("applications");

        String appName = appArr.get(0).toString();

        JsonObject appObj = (JsonObject) jsonParser.parse(appName);

        JsonArray serviceArr = appObj.getAsJsonArray("services");

        String appType = appObj.get("applicationType").toString().replace("\"", "");
//        System.out.println(serviceArr.size());

        if (appType.equals("docker")) {
            System.out.println("Dcoker Agent!!!!");
//            for (int i = 0; i < serviceArr.size(); i++) {
//                ServiceFields serviceFields = new ServiceFields();
//                String services = serviceArr.get(i).toString();
//
//                System.out.println("###" + services);
//                JsonObject serviceObj = (JsonObject) jsonParser.parse(services);
//
////        System.out.println(serviceObj.get("serviceName").toString().replace("\"",""));
//                serviceFields.setUserName(obj.get("userName").toString());
//                serviceFields.setAppplicationType(appObj.get("applicationType").toString().replace("\"", ""));
//                serviceFields.setApplicationIP(appObj.get("ipAddress").toString().replace("\"", ""));
//
//
//                serviceFields.setServiceName(serviceObj.get("serviceName").toString().replace("\"", ""));
//                serviceFields.setServiceType(serviceObj.get("serviceType").toString().replace("\"", ""));
//                serviceFields.setServicePort(serviceObj.get("portNumber").toString().replace("\"", ""));
//
//                String agenturl = "http://" + appObj.get("ipAddress").toString().replace("\"", "") + ":8030/docker/stats";
//
//                serviceFields.setAgentUrl(agenturl);
//
//
//                jobQueue.add(serviceFields);
//
//            }

            ServiceFields dockerService = new ServiceFields();

            dockerService.setUserName(obj.get("userName").toString());
            dockerService.setApplicationIP(appObj.get("ipAddress").toString().replace("\"", ""));
            String agenturl = "http://" + appObj.get("ipAddress").toString().replace("\"", "") + "/docker/stats";
            dockerService.setAgentUrl(agenturl);
            System.out.println("111111111111    docker service data is "+dockerService.toString());
            jobQueue.add(dockerService);

        } else {
            for (int i = 0; i < serviceArr.size(); i++) {

                ServiceFields serviceFields = new ServiceFields();
                String services = serviceArr.get(i).toString();

                System.out.println("###" + services);
                JsonObject serviceObj = (JsonObject) jsonParser.parse(services);

//        System.out.println(serviceObj.get("serviceName").toString().replace("\"",""));
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

        System.out.println("Job Queue Size" + jobQueue.size());
        System.out.println("Entering Job Queue");
        int jobQueueSize = jobQueue.size();
        Thread threadObj;
        for (int i = 0; i < jobQueueSize; i++) {

            System.out.println("NUmb");
//            System.out.println(jobQueue.remove());
            threadObj = new Thread(new ThreadService(jobQueue.remove(), dataCollectorModel, kafkaTemplate, metricFactory));
            threadObj.start();
        }



    }
}



