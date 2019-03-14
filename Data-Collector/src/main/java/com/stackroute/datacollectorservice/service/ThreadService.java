package com.stackroute.datacollectorservice.service;
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
    }

    @Autowired
    public ThreadService(ServiceFields serviceFields, DataCollectorModel dataCollectorModel,KafkaTemplate<String, String> kafkaTemplate, MetricFactory metricFactory) {
        this.dataCollectorModel = dataCollectorModel;
        this.kafkaTemplate = kafkaTemplate;
        this.metricFactory = metricFactory;
        this.serviceFields = serviceFields;
    }

    @Override
    public void run() {

        try {
            executeSampleJob();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    int f=0;

    String agentUrl1 = "";


    public void executeJavaJob() throws Exception {

    }

    public void executeSampleJob() throws Exception {

        logger.info("The docker job has begun...");
        int i=0;


//        Start Of Job

        if(f == 0){
            agentUrl1 = serviceFields.getAgentUrl();
            userName = serviceFields.getUserName();
            try {
                if (!userName.isEmpty() && userName != null) {
                    url.put(serviceFields.getUserName(), agentUrl1);
                    ips.add(serviceFields.getApplicationIP());
                }
            }
            catch(NullPointerException e){
            }
            f = 1;
        }

        for(String url : url.values()) {

            if (!url.isEmpty() && url != null) {
                String response = dataCollectorModel.getMetrics(url);


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
                    appUser = dataCollectorRepository.findUser(ipAddress);
                    List<Application> applicationList = new ArrayList<>();
                    MetricsFinal metricsFinal = new MetricsFinal();
                    applicationList.addAll(appUser.getApplications());
                    for(int l=0;l<applicationList.size();l++) {

                        List<Services> servicesList = new ArrayList<>();

                        servicesList.addAll(applicationList.get(l).getServices());
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

                                kafkaTemplate.send(TOPIC, metricFinalString);

                            }

                        }
                    }

                }

            }

            i++;
        }

        //Java HardCode

        // EO Java Hard Code

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
