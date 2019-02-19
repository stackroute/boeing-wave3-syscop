package com.anjali.dataCollector.Service;

import com.anjali.dataCollector.Model.AgentUrl;
import com.anjali.dataCollector.Model.DataCollectorModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SampleJobService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AgentUrl agentUrl;

    @Autowired
    private DataCollectorModel dataCollectorModel;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "Kafka_Example_Test_Final";

    public void executeSampleJob() {

        logger.info("The sample job has begun...");

//        Start Of Job

        agentUrl.setUrl ("http://172.23.239.162:8020/docker/stats");
        String response = dataCollectorModel.getMetrics (agentUrl.getUrl());
        kafkaTemplate.send(TOPIC, response);

//        End Of Job
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("Error while executing sample job", e);
        } finally {
            logger.info("------------Sample job has finished-----------");
        }
    }
}
