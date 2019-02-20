package com.syscop.monitoringService.monitoring.service.MonitoringController;


import com.syscop.monitoringService.monitoring.service.KafkaListnerService.KafkaListenerSer;
import com.syscop.monitoringService.monitoring.service.Model.Monitor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/v1/")
public class MonController {

    @Autowired
    private KafkaListenerSer kafkaListenerSer;


    private  Monitor monitor;

    @Autowired
    public MonController(Monitor monitor) {
        this.monitor = monitor;
    }

    @GetMapping("data")
    public ResponseEntity<?> getMonitorData(){

        System.out.println (monitor);
        return new ResponseEntity<>(monitor, HttpStatus.OK);

    }





}
