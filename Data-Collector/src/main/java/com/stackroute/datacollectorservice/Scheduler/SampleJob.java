package com.stackroute.datacollectorservice.Scheduler;
import com.stackroute.datacollectorservice.service.ThreadService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SampleJob implements Job {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ThreadService jobService;

    public void execute(JobExecutionContext context) throws JobExecutionException {

        logger.info("Job ** {} ** fired @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());

        try {
            jobService.executeSampleJob();
            jobService.executeJavaJob();
        } catch (Exception e) {
            e.printStackTrace();
        }


        logger.info("Next job scheduled @ {}", context.getNextFireTime());

    }
}