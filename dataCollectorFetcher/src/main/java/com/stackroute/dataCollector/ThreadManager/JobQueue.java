package com.stackroute.dataCollector.ThreadManager;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class JobQueue {

    private Queue<JobModel> queue = new LinkedList<>();

    public void addJob(JobModel jobModel){

        queue.add(jobModel);

    }

    public JobModel popJob(){

        return queue.remove();

    }

    public int getSize(){
        return queue.size();
    }

}
