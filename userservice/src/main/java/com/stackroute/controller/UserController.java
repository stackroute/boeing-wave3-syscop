package com.stackroute.controller;

import com.stackroute.model.User;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.exception.UserNotFoundException;
import com.stackroute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    private static final String TOPIC = "Kafka_NewUser_Registration";
    private UserService userService;
    private KafkaTemplate<String, User> kafkaTemplate;

    @Autowired
    public UserController(UserService userService, KafkaTemplate<String, User> kafkaTemplate) {
        this.userService = userService;
        this.kafkaTemplate = kafkaTemplate;
    }

    //Request mapping for posting user details
    @PostMapping("register")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        ResponseEntity responseEntity;
        try {
            userService.saveUser(user);
            responseEntity = new ResponseEntity<String>("Successfully created the User!!", HttpStatus.CREATED);
            kafkaTemplate.send(TOPIC, user);
        }
        catch (UserAlreadyExistsException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

//Request mapping for getting user details
    @GetMapping("users")
    public ResponseEntity<List<User>> listOfUsers() {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
    }

//Request mapping for deleting user details
    @DeleteMapping("users/{cid}")
    public ResponseEntity<?> deleteUser(@PathVariable("cid") String userId){
        ResponseEntity responseEntity;
        try {
            User user = userService.deleteUser(userId);
            responseEntity = new ResponseEntity<User>(user, HttpStatus.GONE);
        }
        catch (UserNotFoundException userNotFoundException){
            responseEntity = new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);

        }
        return responseEntity;
    }
    //Request mapping for updating user details
    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String userId, @RequestBody User user) {
        ResponseEntity responseEntity;
        try{
            User user1 = userService.updateUser(userId,user);
            responseEntity = new ResponseEntity<User>(user1, HttpStatus.OK);
        }
        catch (UserNotFoundException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        return responseEntity;

    }


}
