package com.stackroute.processorservice.service;

import com.stackroute.processorservice.FactoryModel.MetricFactory;
import com.stackroute.processorservice.MetricModel.MetricInterface;
import com.stackroute.processorservice.model.AgentUrl;
import com.stackroute.processorservice.model.DataCollectorModel;
import com.stackroute.processorservice.model.ServiceFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ThreadService implements Runnable {

    private static final String TOPIC = "Kafka_Example_Test_Thread";


    private ServiceFields serviceFields;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private KafkaTemplate<String, String> kafkaTemplate;

    private MetricFactory metricFactory;

    private MetricInterface metricObject;

    private DataCollectorModel dataCollectorModel;

    private static String url;

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



//        Start Of Job
//        System.out.println("AgentURl" + serviceFields.getAgentUrl());
        System.out.println(dataCollectorModel);
    //   String response = dataCollectorModel.getMetrics(serviceFields.getAgentUrl());
        System.out.println("11111" + serviceFields.toString());
        System.out.println("22222 "+serviceFields.getAgentUrl());
        if(f == 0){
            System.out.println("In agent URl" + f);
            agentUrl1 = serviceFields.getAgentUrl();
            url = agentUrl1;
            f = 1;
        }

          System.out.println("I am url" + url +" " +f);
        if(!url.isEmpty() && url!=null) {
            System.out.println("NONONONONONON");
            System.out.println("333333" + agentUrl1);
//           DataCollectorModel dataCollectorModel = new DataCollectorModel();
            String response = dataCollectorModel.getMetrics(url);
            System.out.println("!!@@" + agentUrl1);
            System.out.println(response);

//            metricObject = metricFactory.createObject("dockermetric");
//            System.out.println(response);
//
//            metricObject.parse(response);
//            System.out.println("Response=====");
//            System.out.println(response);

            kafkaTemplate.send(TOPIC, response);
        }

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
