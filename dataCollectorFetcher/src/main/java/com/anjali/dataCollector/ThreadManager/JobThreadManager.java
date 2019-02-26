package com.anjali.dataCollector.ThreadManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobThreadManager {
    private int maxThread = 2;
    private JobQueue jobQueue;

    @Autowired
    public JobThreadManager(JobQueue jobQueue) {
        this.jobQueue = jobQueue;
    }
}
