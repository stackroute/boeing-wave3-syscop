package com.stackroute.dataCollector.Service;

import com.stackroute.dataCollector.FactoryModel.MetricFactory;
import com.stackroute.dataCollector.MetricModel.MetricInterface;
import com.stackroute.dataCollector.Model.AgentUrl;
import com.stackroute.dataCollector.Model.DataCollectorModel;
import com.stackroute.dataCollector.ThreadManager.JobThreadManager;
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

    private JobThreadManager jobThreadManager;

    @Autowired
    public SampleJobService(JobThreadManager jobThreadManager, AgentUrl agentUrl, DataCollectorModel dataCollectorModel, KafkaTemplate<String, String> kafkaTemplate, MetricFactory metricFactory) {
        this.agentUrl = agentUrl;
        this.dataCollectorModel = dataCollectorModel;
        this.kafkaTemplate = kafkaTemplate;
        this.metricFactory = metricFactory;
        this.jobThreadManager = jobThreadManager;
    }

    private static final String TOPIC = "Kafka_Example_Test_Final1";

    public void executeSampleJob() {

        logger.info("The docker job has begun...");

//        Start Of Job

        jobThreadManager.startJob();

//        agentUrl.setUrl ("http://172.23.239.162:8020/docker/stats");
//        String response = dataCollectorModel.getMetrics (agentUrl.getUrl());

//        metricObject = metricFactory.createObject ("dockermetric");

//        metricObject.parse (response);

//        kafkaTemplate.send(TOPIC, metricObject.toString ());

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
