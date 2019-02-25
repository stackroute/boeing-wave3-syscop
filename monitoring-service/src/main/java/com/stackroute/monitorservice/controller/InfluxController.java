package com.stackroute.monitorservice.controller;


import com.stackroute.monitorservice.model.Monitor;
import com.stackroute.monitorservice.service.InfluxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class InfluxController {

    private InfluxService influxService;

    @Autowired
    public Monitor monitor;

    @Autowired
    public InfluxController(InfluxService influxService) {
        this.influxService = influxService;
    }

    //get method to get the metrics data
    @GetMapping("data")
    public ResponseEntity<?> getMonitorData(){

        System.out.println (monitor);
        return new ResponseEntity<>(monitor, HttpStatus.OK);

    }
}
