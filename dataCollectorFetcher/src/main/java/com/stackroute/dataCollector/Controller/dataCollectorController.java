package com.stackroute.dataCollector.Controller;

//import AgentUrl;
import com.stackroute.dataCollector.Model.DataCollectorModel;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("data/")
public class dataCollectorController {

    private static final String TOPIC = "Kafka_Example_Test_Final";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private DataCollectorModel dataCollectorModel;
    private ResponseEntity<?> responseEntity = null;

    @Autowired
    public dataCollectorController(DataCollectorModel dataCollectorModel) {
        this.dataCollectorModel = dataCollectorModel;
    }

//    @PostMapping("api")
//    public ResponseEntity<?> collectMetric(@RequestBody AgentUrl agentUrl){ //request param
//        String response = this.dataCollectorModel.getMetrics (agentUrl.getUrl());
//
//      this.responseEntity = new ResponseEntity<> (response, HttpStatus.OK);
//        kafkaTemplate.send(TOPIC, response);
//        return responseEntity;
//    }

}









