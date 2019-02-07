package com.stackroute.nextevent.service;

import com.stackroute.nextevent.exception.EventAlreadyExist;
import com.stackroute.nextevent.exception.EventNotFound;
import com.stackroute.nextevent.model.Event;

import java.util.List;

public interface EventService {

    Event saveEvent(Event event) throws EventAlreadyExist;
    boolean deleteEvent(String id) throws EventNotFound;
    Event updateEvent(Event event,String id) throws EventNotFound;
    Event getEventById(String id) throws EventNotFound;
    List<Event> getAllEvent() throws EventNotFound;

}
