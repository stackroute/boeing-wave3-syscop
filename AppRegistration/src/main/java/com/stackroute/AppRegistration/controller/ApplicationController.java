package com.stackroute.AppRegistration.controller;

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
@RequestMapping(value = "syscop/appregistration")
public class ApplicationController {

    ApplicationService applicationService;

    private KafkaTemplate<String,String> kafkaTemplate;

    private static final String TOPIC = "kafkaAppRegistration";

    @Autowired
    public ApplicationController(ApplicationService applicationService, KafkaTemplate<String, String> kafkaTemplate) {
        this.applicationService = applicationService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping(value = "new")
    public ResponseEntity<?> newAppRegistration(@RequestBody User userObj) throws ApplicationAlreadyExistException{
        ResponseEntity responseEntity;
        User resultUserObj = applicationService.addApplication(userObj);

        kafkaTemplate.send(TOPIC,resultUserObj.toString());
        responseEntity = new ResponseEntity<String>("User-Application Registration Successfull", HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(value = "showApp/username/{userName}")
    public ResponseEntity<?> showAllRegistrations(@PathVariable (value = "userName") String userName) throws ApplicationDoesNotExistException {
        System.out.println(applicationService.getAllApplications(userName));
        return new ResponseEntity<User>(applicationService.getAllApplications(userName),HttpStatus.OK);
    }
}
