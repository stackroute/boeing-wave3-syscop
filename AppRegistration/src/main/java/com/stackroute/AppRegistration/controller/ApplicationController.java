package com.stackroute.AppRegistration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.AppRegistration.domain.User;
import com.stackroute.AppRegistration.exceptions.ApplicationAlreadyExistException;
import com.stackroute.AppRegistration.exceptions.ApplicationDoesNotExistException;
import com.stackroute.AppRegistration.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/v2/syscop/appregistration")
public class ApplicationController {

    private ApplicationService applicationService;

    private KafkaTemplate<String,String> kafkaTemplate;

    private static final String TOPIC = "kafkaAppRegistration";


    @Autowired
    public ApplicationController(ApplicationService applicationService, KafkaTemplate<String, String> kafkaTemplate) {
        this.applicationService = applicationService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping(value = "new")
    public ResponseEntity<?> newAppRegistration(@RequestBody User userObj) throws ApplicationAlreadyExistException, JsonProcessingException {
        ResponseEntity responseEntity;
        User resultUserObj = applicationService.addApplication(userObj);

        ObjectMapper obj = new ObjectMapper();
        String jsonStr = obj.writeValueAsString(resultUserObj);
        //kafkaTemplate.send(TOPIC, jsonStr);

        responseEntity = new ResponseEntity<String>("User-Application Registration Successfull", HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(value = "showApp/username/{userName}")
    public ResponseEntity<?> showAllRegistrations(@PathVariable (value = "userName") String userName) throws ApplicationDoesNotExistException {
        System.out.println(applicationService.getAllApplications(userName));
        return new ResponseEntity<User>(applicationService.getAllApplications(userName),HttpStatus.OK);
    }

    @PutMapping(value = "updateApp")
    public ResponseEntity<?> updateApplication(@RequestBody User userObj) throws ApplicationDoesNotExistException, JsonProcessingException{
        ResponseEntity responseEntity;
        User resultUserObj = applicationService.updateApplications(userObj);

        ObjectMapper obj = new ObjectMapper();
        String jsonStr = obj.writeValueAsString(resultUserObj);
        //kafkaTemplate.send(TOPIC, jsonStr);

        responseEntity = new ResponseEntity<String>("Application Update Successfull", HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(value = "deleteApp")
    public ResponseEntity<?> deleteApplication(@RequestBody User userObj) throws ApplicationDoesNotExistException, JsonProcessingException{
        ResponseEntity responseEntity;
        User resultUserObj = applicationService.deleteApplication(userObj);

        ObjectMapper obj = new ObjectMapper();
        String jsonStr = obj.writeValueAsString(resultUserObj);
        //kafkaTemplate.send(TOPIC, jsonStr);

        responseEntity = new ResponseEntity<String>("Application Deletion Successfull", HttpStatus.OK);
        return responseEntity;

    }
}
