package com.stackroute.processorservice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.processorservice.model.Person;
import com.stackroute.processorservice.service.InfluxService;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class InfluxController {

    private InfluxService influxService;

    @Autowired
    public InfluxController(InfluxService influxService) {
        this.influxService = influxService;
    }

    @PostMapping(value = "data")
    public ResponseEntity<?> saveData(@RequestBody Person person) {
        System.out.println(person.getName() + "######");
        Person person1 = new Person();
        try {

            person1 = influxService.savePerson(person);

        }
        catch (Exception e){
            System.out.println(e);
        }

        return new ResponseEntity<Person>(person1, HttpStatus.OK);
    }

    @GetMapping(value = "query")
    public ResponseEntity<?> queryInfluxDB() throws JsonProcessingException {
        QueryResult queryResult1 = influxService.calculateAllThreshold();
        return new ResponseEntity<QueryResult>(queryResult1,HttpStatus.OK);
    }
}
