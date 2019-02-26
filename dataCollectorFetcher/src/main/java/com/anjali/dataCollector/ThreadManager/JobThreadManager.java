package com.anjali.dataCollector.ThreadManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobThreadManager {
    private int maxThread = 1;
    private int threadCount = 0;
    private JobQueue jobQueue;

    @Autowired
    public JobThreadManager(JobQueue jobQueue) {

        this.jobQueue = jobQueue;

//        generate dummy data
        JobModel tmp = new JobModel();

        tmp.setUserId("Vaibhav");
        tmp.setAgentType("dockermetric");
        tmp.setAgentAPI("http://blahblah");

        this.jobQueue.addJob(tmp);

        tmp = new JobModel();
        tmp.setUserId("Ramu");
        tmp.setAgentType("dockermetric");
        tmp.setAgentAPI("http://blahblah");

        this.jobQueue.addJob(tmp);

        tmp = new JobModel();
        tmp.setUserId("Shyamu");
        tmp.setAgentType("javametric");
        tmp.setAgentAPI("http://blahblah");

        this.jobQueue.addJob(tmp);
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void incThreadCount() {
        threadCount ++;
    }

    public void decThreadCount() {
        threadCount --;
    }

    public void startJob() {
        if(jobQueue.getSize()>0 && threadCount<maxThread){
            JobThread newJob = new JobThread(this);
            JobModel newJobModel = jobQueue.popJob();
            newJob.setJobModel(newJobModel);
            incThreadCount();

            newJob.run();
            jobQueue.addJob(newJobModel);
        }
    }

}
