package com.stackroute.processorservice.controller;


import com.stackroute.processorservice.model.Person;
import com.stackroute.processorservice.service.InfluxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class InfluxController {

    private InfluxService influxService;

    @Autowired
    public InfluxController(InfluxService influxService) {
        this.influxService = influxService;
    }

    //Controller Test Method to check data persistence in InfluxDB
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
}
