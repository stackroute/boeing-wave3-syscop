package com.stackroute.nextevent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Specified Event Not Found on server")
public class EventNotFound extends Exception {

    public EventNotFound(String message){super(message);}

}
