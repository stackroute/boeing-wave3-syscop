package com.stackroute.alert.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stackroute.alert.repository.UserRepository;
import com.stackroute.alert.job.EmailJob;
import com.stackroute.alert.model.User;
import com.stackroute.alert.payload.ScheduleEmailRequest;
import com.stackroute.alert.payload.ScheduleEmailResponse;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class KafkaListenerService {

    @Autowired
    SimpMessagingTemplate template;

    @Autowired(required = true)
    private UserRepository userRepository;
    @Autowired
    private Scheduler scheduler;

    public static final String ACCOUNT_SID = "AC6d4ebda348d5a8d3bc56841f2810ba04";
    public static final String AUTH_TOKEN  = "5527cd087017ac830f59a872e36cf440";
    // Create a phone number in the Twilio console.................
    public static final String TWILIO_NUMBER = "+12017786294";


    //Listener for User Registration service to store user details like username,email & phone number
    //in local mysql.
    @KafkaListener(topics = "Kafka_NewUser_Registration", groupId = "group_id")
    public void consumeUserRegData(String message) {
        String[] strMessage = message.split(",");
        User user = new User();

        user.setEmail(strMessage[4].split(":")[1].replace("\"",""));
        user.setUsername(strMessage[1].split(":")[1].replace("\"",""));
        user.setPhoneNumber(strMessage[3].split(":")[1].replace("\"",""));

        userRepository.save(user);

    }

    /*
        A JobDetail represents an instance of a Job.It also contains additional data
        in the form of a JobDataMap that is passed to the Job when it is executed.
     */
    private JobDetail buildJobDetail(ScheduleEmailRequest scheduleEmailRequest) {
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("email", scheduleEmailRequest.getEmail());
        jobDataMap.put("subject", scheduleEmailRequest.getSubject());
        jobDataMap.put("body", scheduleEmailRequest.getBody());

        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Send Email Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    /*
    TRIGGERS defines the schedule at which a given Job will be executed. A Job can have many Triggers,
    but a Trigger can only be associated with one Job.
     */
    private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
                .withDescription("Send Email Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }

    //Listener for Threshold service to get the details of metrics that have crossed their threshold
    // value and then it will send the alert to the respective user by matching username from locally s
    // tored database and getting email and phone number from their only.

    @KafkaListener(topics = "Kafka_Example_Alert", groupId = "group_id2")
    public void consumeThresholdData(String message) {

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(message);

        User user = userRepository.getUserDetails(jsonObject.get("userName").toString().replace("\"",""));

        template.convertAndSend("/topic/temperature",jsonObject.get("alert").toString()+ jsonObject.get("serviceName").toString());

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message textMessage = Message.creator(
                new PhoneNumber(user.getPhoneNumber()),
                new PhoneNumber(TWILIO_NUMBER),
                jsonObject.get("alert").toString()+ jsonObject.get("serviceName").toString()

                )
                .create();


        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        ResourceSet<Message> messages = Message.reader().read();
        for (Message message1 : messages) {
        }

        ScheduleEmailRequest scheduleEmailRequest= new ScheduleEmailRequest();

        try {

            ZonedDateTime currentdateTime = ZonedDateTime.now();        //to get current date and time.......
            ZonedDateTime dateTime = currentdateTime.plusSeconds(5);

            scheduleEmailRequest.setEmail(user.getEmail());
            scheduleEmailRequest.setBody(jsonObject.get("alert").toString()+ jsonObject.get("serviceName").toString());
            scheduleEmailRequest.setSubject("Syscop Alert");

            JobDetail jobDetail = buildJobDetail(scheduleEmailRequest);

            Trigger trigger = buildJobTrigger(jobDetail, dateTime);
            scheduler.scheduleJob(jobDetail, trigger);

            ScheduleEmailResponse scheduleEmailResponse = new ScheduleEmailResponse(true,
                    jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), "Email Scheduled Successfully!");
        } catch (SchedulerException ex) {

            ScheduleEmailResponse scheduleEmailResponse = new ScheduleEmailResponse(false,
                    "Error scheduling email. Please try later!");
        }

    }


}
