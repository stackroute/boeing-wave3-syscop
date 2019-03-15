package com.stackroute.datacollectorservice.service;
//////////////////////////


///////////////////////////////
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stackroute.datacollectorservice.FactoryModel.MetricFactory;
import com.stackroute.datacollectorservice.MetricModel.MetricInterface;
import com.stackroute.datacollectorservice.model.*;
import com.stackroute.datacollectorservice.repository.DataCollectorRepository;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class ThreadService implements Runnable {

    private static final String TOPIC = "Kafka_Example_Test_Final3";

    @Autowired
    private DataCollectorRepository dataCollectorRepository;

    private ServiceFields serviceFields;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private KafkaTemplate<String, String> kafkaTemplate;

    private MetricFactory metricFactory;

    private MetricInterface metricObject;

    private DataCollectorModel dataCollectorModel;

    private static Map<String, String> url = new LinkedHashMap<>();
    private static List<String> ips = new ArrayList<>();
    private static  String userName;

    public ThreadService(){
        System.out.println("in not arg constructor");
    }

    @Autowired
    public ThreadService(ServiceFields serviceFields, DataCollectorModel dataCollectorModel,KafkaTemplate<String, String> kafkaTemplate, MetricFactory metricFactory) {
        System.out.println("in all arg constructor");
        this.dataCollectorModel = dataCollectorModel;
        this.kafkaTemplate = kafkaTemplate;
        this.metricFactory = metricFactory;
        this.serviceFields = serviceFields;
    }

    @Override
    public void run() {

        System.out.println("IN THREAD");
        try {
            executeSampleJob();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    int f=0;

    String agentUrl1 = "";


    public void executeJavaJob() throws Exception {
        System.out.println("Executing Java Job.....");

    }

    public void executeSampleJob() throws Exception {

        logger.info("The docker job has begun...");
        int i=0;


//        Start Of Job
//        System.out.println("AgentURl" + serviceFields.getAgentUrl());
        System.out.println(dataCollectorModel);
    //   String response = dataCollectorModel.getMetrics(serviceFields.getAgentUrl());
        System.out.println("11111" + serviceFields.toString());
        System.out.println("22222 "+serviceFields.getAgentUrl());
        if(f == 0){
            System.out.println("In agent URl" + f);
            agentUrl1 = serviceFields.getAgentUrl();
            System.out.println("AgentURl" + agentUrl1);
            userName = serviceFields.getUserName();
            try {
                if (!userName.isEmpty() && userName != null) {
                    url.put(serviceFields.getUserName(), agentUrl1);
                    System.out.println(url.size());
                    ips.add(serviceFields.getApplicationIP());
                    System.out.println(ips.get(0));
                }
            }
            catch(NullPointerException e){
                System.out.println("UserName is NULL");
            }
            f = 1;
        }

        System.out.println("UserNAme" + userName);
//        for(int i=1;i<url.size();i++) {
        for(String url : url.values()) {

            System.out.println("I am url" + url + " " + f);
            if (!url.isEmpty() && url != null) {
                System.out.println("NONONONONONON");
                System.out.println("333333" + url);
//           DataCollectorModel dataCollectorModel = new DataCollectorModel();
                String response = dataCollectorModel.getMetrics(url);
                System.out.println("!!@@" + agentUrl1);
                System.out.println(response);


//                List<User> userList = new ArrayList<>();
                User appUser = new User();
                JsonParser jsonParser = new JsonParser();
                ObjectMapper objMapper = new ObjectMapper();

                JsonArray metricArr = (JsonArray) jsonParser.parse(response);

                Gson gson = new Gson();

                for (int j = 0; j < metricArr.size(); j++) {

                    JsonObject dockerJsonObj = (JsonObject) metricArr.get(j);

                    String jsonString = dockerJsonObj.toString();

                    Metrics metrics = gson.fromJson(jsonString, Metrics.class);

                    int port = Integer.parseInt(dockerJsonObj.get("port").toString().replace("\"", ""));

                    String ipAddress = ips.get(i).replace("\"","");
                    System.out.println("IPADDRESS" + ipAddress);
                    appUser = dataCollectorRepository.findUser(ipAddress);
                    System.out.println(appUser.toString());
                    List<Application> applicationList = new ArrayList<>();
                    MetricsFinal metricsFinal = new MetricsFinal();
                    applicationList.addAll(appUser.getApplications());
                    System.out.println("aassssssss" + applicationList.size());
                    for(int l=0;l<applicationList.size();l++) {

                        System.out.println("Inside application loop");
                        List<Services> servicesList = new ArrayList<>();

                        servicesList.addAll(applicationList.get(l).getServices());
//                    System.out.println("!!@@@@#####" + application.getServices().toString());
//                    servicesList.addAll(application.getServices());
                        System.out.println(servicesList.size());
                        for (int k = 0; k < servicesList.size(); k++) {

                            Services services = new Services();

                            services = servicesList.get(k);
                            if (services.getPortNumber() == port) {

                                metricsFinal.setUserName(appUser.getUserName());
                                metricsFinal.setServiceName(services.getServiceName());
                                metricsFinal.setServiceType(services.getServiceType());
                                metricsFinal.setPortNumber(port);
                                metricsFinal.setMetrics(metrics);

                                String metricFinalString = objMapper.writeValueAsString(metricsFinal);

                                System.out.println("MetricFinalString" + metricFinalString);
                                kafkaTemplate.send(TOPIC, metricFinalString);

                            }


                        }


                    }

                }


//            for(int i=0;i<userList.size();i++){
//
//                JsonObject dockerJsonObj = (JsonObject) metricArr.get(i);
//                User user = userList.get(i);
//
//
////                System.out.println(it.next().toString());
//
//
//            }


//            metricObject = metricFactory.createObject("dockermetric");
//            System.out.println(response);
//
//            metricObject.parse(response);
//            System.out.println("Response=====");
//            System.out.println(response);

//                kafkaTemplate.send(TOPIC, response);
            }

            i++;
        }


        //Java HardCode

          String responseJava = dataCollectorModel.getMetrics("https://10.20.1.44:8003/metrics");

          MetricInterface javaMetric = metricFactory.createObject("javametric");
          javaMetric.parse(responseJava);
         System.out.println(javaMetric.toString());
        

//        EO Java Hard Code

        //End Of Job
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("Error while executing sample job", e);
        } finally {
            logger.info("---------The docker job has finished-----------");
        }
    }


}
