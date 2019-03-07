//package com.stackroute.monitorservice.controller;
//
//
//import com.stackroute.monitorservice.model.Monitor;
//import com.stackroute.monitorservice.service.InfluxService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin(origins="*")
//@RestController
//@RequestMapping("api/v1/")
//public class InfluxController {
//
//    private Monitor monitor;

//    @Autowired
//    public InfluxController( Monitor monitor) {
//        this.monitor = monitor;
//    }
//
//    //get api to get the metrics data from datacollector
//    @GetMapping("data")
//    public ResponseEntity<?> getMonitorData(){
//
//        System.out.println (monitor);
//        return new ResponseEntity<>(monitor, HttpStatus.OK);
//
//    }
//}
