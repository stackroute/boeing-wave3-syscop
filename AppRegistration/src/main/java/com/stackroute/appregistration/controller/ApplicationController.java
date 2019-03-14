package com.stackroute.appregistration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.appregistration.domain.User;
import com.stackroute.appregistration.exceptions.ApplicationAlreadyExistException;
import com.stackroute.appregistration.exceptions.ApplicationDoesNotExistException;
import com.stackroute.appregistration.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


/**
 * This is a controller layer on whose methods user hits from UI
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/v2/syscop/appregistration")
public class ApplicationController {

    private ApplicationService applicationService;

    //kafkatemplate provides a template as a high-level abstraction for sending messages.
    //As Kafka stores and transports Byte arrays, we need to specify the format from which the key and value will be serialized.
    //The KafkaTemplate wraps a producer and provides convenience methods to send data to kafka topics.
    private KafkaTemplate<String,String> kafkaTemplate;

    private static final String TOPIC = "kafkaAppRegistration";


    @Autowired
    public ApplicationController(ApplicationService applicationService, KafkaTemplate<String, String> kafkaTemplate) {
        this.applicationService = applicationService;
        this.kafkaTemplate = kafkaTemplate;
    }

    //Registration for new application/s
    @PostMapping(value = "new")
    public ResponseEntity<?> newAppRegistration(@RequestBody User userObj) throws ApplicationAlreadyExistException, JsonProcessingException {
        ResponseEntity responseEntity;
        User resultUserObj = applicationService.addApplication(userObj);


        ObjectMapper obj = new ObjectMapper();
        String jsonStr = obj.writeValueAsString(resultUserObj);
        kafkaTemplate.send(TOPIC, jsonStr);


        responseEntity = new ResponseEntity<String>("User-Application Registration Successfull", HttpStatus.CREATED);
        return responseEntity;
    }

    //Displays all the registered applications
    @GetMapping(value = "showApp/username/{userName}")
    public ResponseEntity<?> showAllRegistrations(@PathVariable (value = "userName") String userName) throws ApplicationDoesNotExistException {
        return new ResponseEntity<User>(applicationService.getAllApplications(userName),HttpStatus.OK);
    }

    //Edits application details
    @PutMapping(value = "updateApp")
    public ResponseEntity<?> updateApplication(@RequestBody User userObj) throws ApplicationDoesNotExistException, JsonProcessingException{
        ResponseEntity responseEntity;
        User resultUserObj = applicationService.updateApplications(userObj);

        ObjectMapper obj = new ObjectMapper();
        String jsonStr = obj.writeValueAsString(resultUserObj);
        kafkaTemplate.send(TOPIC, jsonStr);

        responseEntity = new ResponseEntity<String>("Application Update Successfull", HttpStatus.OK);
        return responseEntity;
    }

    //Deletes application
    @DeleteMapping(value = "deleteApp")
    public ResponseEntity<?> deleteApplication(@RequestBody User userObj) throws ApplicationDoesNotExistException, JsonProcessingException{
        ResponseEntity responseEntity;
        User resultUserObj = applicationService.deleteApplication(userObj);

        ObjectMapper obj = new ObjectMapper();
        String jsonStr = obj.writeValueAsString(resultUserObj);
        kafkaTemplate.send(TOPIC, jsonStr);

        responseEntity = new ResponseEntity<String>("Application Deletion Successfull", HttpStatus.OK);
        return responseEntity;

    }
}
