package com.anjali.dataCollector.Service;

import com.anjali.dataCollector.FactoryModel.MetricFactory;
import com.anjali.dataCollector.MetricModel.MetricInterface;
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

    private AgentUrl agentUrl;

    private DataCollectorModel dataCollectorModel;

    private KafkaTemplate<String, String> kafkaTemplate;

    private MetricFactory metricFactory;
    private MetricInterface metricObject;

    @Autowired
    public SampleJobService(AgentUrl agentUrl, DataCollectorModel dataCollectorModel, KafkaTemplate<String, String> kafkaTemplate, MetricFactory metricFactory) {
        this.agentUrl = agentUrl;
        this.dataCollectorModel = dataCollectorModel;
        this.kafkaTemplate = kafkaTemplate;
        this.metricFactory = metricFactory;
    }

    private static final String TOPIC = "Kafka_Example_Test_Final";

    public void executeSampleJob() {

        logger.info("The docker job has begun...");

//        Start Of Job

        agentUrl.setUrl ("http://172.23.239.162:8020/docker/stats");
        String response = dataCollectorModel.getMetrics (agentUrl.getUrl());

        metricObject = metricFactory.createObject ("dockermetric");

        metricObject.parse (response);

        kafkaTemplate.send(TOPIC, metricObject.toString ());

//        End Of Job
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("Error while executing sample job", e);
        } finally {
            logger.info("---------The docker job has finished-----------");
        }
    }
}