package com.stackroute.nextevent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Event already exists on server")
public class EventAlreadyExist extends Exception {
    public EventAlreadyExist(String message){super(message);}
}
