package com.stackroute.zuulgatewayservice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ApiController {

    @GetMapping("zuul")
    public ResponseEntity<?> test() {

        return new ResponseEntity<String>("Hello Zuul", HttpStatus.OK);

    }


}
