package com.anjali.dataCollector.ThreadManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobThreadManager {
    private int maxThread = 2;
    private int threadCount = 0;
    private JobQueue jobQueue;

    @Autowired
    public JobThreadManager(JobQueue jobQueue) {
        this.jobQueue = jobQueue;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }
}
