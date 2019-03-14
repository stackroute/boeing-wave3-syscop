package com.stackroute.monitorservice.controller;


import com.stackroute.monitorservice.model.HistoricalDockerMetric;
import com.stackroute.monitorservice.model.Monitor;
import com.stackroute.monitorservice.model.Range;
import com.stackroute.monitorservice.service.InfluxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("api/v1/")
public class InfluxController {

    private Monitor monitor;
    private InfluxServiceImpl influxServiceImpl;

    @Autowired
    public InfluxController( Monitor monitor, InfluxServiceImpl influxServiceImpl )
    {
        this.monitor = monitor;
        this.influxServiceImpl = influxServiceImpl;
    }

    //get api to get the metrics data from datacollector
    @GetMapping("data")
    public ResponseEntity<?> getMonitorData(){

        return new ResponseEntity<>(monitor, HttpStatus.OK);
    }

    @GetMapping("hi")
    public ResponseEntity<?> test() {
        return new ResponseEntity<String>("Hello Monitor", HttpStatus.OK);
    }

    @PostMapping("history")
    public ResponseEntity<?> getHistoricalData(@RequestBody Range range) throws ParseException {


       List<HistoricalDockerMetric> list = new ArrayList<>();

        list = influxServiceImpl.getHistoricalMetrics(range);

        return  new ResponseEntity<List<HistoricalDockerMetric>>(list, HttpStatus.OK);
    }
}
