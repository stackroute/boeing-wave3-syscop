package com.anjali.dataCollector.KafkaService;


import com.anjali.dataCollector.ThreadManager.JobModel;
import com.anjali.dataCollector.ThreadManager.JobQueue;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    private JobQueue jobQueue;
    private JobModel jobModel;

    @Autowired
    public KafkaListenerService(JobQueue jobQueue) {
        this.jobQueue = jobQueue;
    }

    @KafkaListener(topics = "kafkaAppRegistration", groupId = "group_id2")
    public void consume(String message) throws JsonProcessingException {
        System.out.println("Consumed msg : " + message);
        //Create job in jobqueue
//        jobQueue.addJob(jobModel);
    }
}
