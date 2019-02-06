package com.stackroute.nextevent.controller;

import com.stackroute.nextevent.exception.EventAlreadyExist;
import com.stackroute.nextevent.exception.EventNotFound;
import com.stackroute.nextevent.model.Event;
import com.stackroute.nextevent.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class EventController {
    private static Logger log = LoggerFactory.getLogger(EventController.class);

    private ResponseEntity responseEntity;
    private EventService eventService;

    @Autowired
    private EventController(final EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("events")
    public ResponseEntity<?> getAllEvent() throws EventNotFound {
            log.info("getAllEvevnt hit");
            responseEntity = new ResponseEntity(eventService.getAllEvent(), HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("events")
    public ResponseEntity<?> saveEvent(@RequestBody Event event) throws EventAlreadyExist{
            eventService.saveEvent(event);
            responseEntity = new ResponseEntity(event, HttpStatus.CREATED);

        return responseEntity;
    }

    @DeleteMapping("events/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") String id) throws EventNotFound {
            eventService.deleteEvent(id);
            log.info("event mapping hit event deleted======");
            responseEntity = new ResponseEntity("Event deleted successfully", HttpStatus.OK);
        return responseEntity;
    }


    @PutMapping("events/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable("id") String id,@RequestBody Event event) throws EventNotFound {
            log.info("upadte event hit");
           Event event1= eventService.updateEvent(event,id);
           log.info("updated event+++++"+event1);
        return responseEntity;
    }

    @GetMapping("event/{id}")
    public ResponseEntity<?> getEventById(@PathVariable("id") String id) throws EventNotFound {
            log.info("getEvent hit by Id");
            responseEntity = new ResponseEntity(eventService.getEventById(id), HttpStatus.OK);
        return responseEntity;
    }
}































