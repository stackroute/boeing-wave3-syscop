package com.anjali.dataCollector.ThreadManager;

import org.springframework.beans.factory.annotation.Autowired;

public class JobThread implements Runnable{

    private JobThreadManager jobThreadManager;
    private JobModel jobModel;

    @Autowired
    public JobThread(JobThreadManager jobThreadManager) {

        this.jobThreadManager = jobThreadManager;
    }

    public void setJobModel(JobModel jobModel) {
        this.jobModel = jobModel;
    }

    @Override
    public void run() {
        System.out.println(jobModel.toString());
        jobThreadManager.decThreadCount();
    }
}
