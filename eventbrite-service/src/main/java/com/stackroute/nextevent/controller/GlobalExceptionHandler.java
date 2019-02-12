package com.stackroute.nextevent.controller;

import com.stackroute.nextevent.exception.EventAlreadyExist;
import com.stackroute.nextevent.exception.EventNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
//@Log4j
public class GlobalExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ResponseStatus(value = HttpStatus.CONFLICT,reason = "Event Already Exists")
    @ExceptionHandler(EventAlreadyExist.class)
    public void handleEventAlreadyExists(EventAlreadyExist e)
    {
         log.error("Event alredy Exists");
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Event Not Found")
    @ExceptionHandler(EventNotFound.class)
    public void handleEventNotFound()
    {
        log.error("Event Not Found");
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR,reason = "Something went wrong ")
    public void handleException(Exception e)
    {
        log.error("Try!!! After sometime");
    }
//   @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Not all mandatory fields are filled")
//   @ExceptionHandler(EmptyFieldException.class)
//   public void handleEmptyFieldException(EmptyFieldException e){
//       log.error("Not all mandatory fields are filled", e);
//   }
//
//    @ResponseStatus(value= HttpStatus.CONFLICT, reason="User already exists")
//    @ExceptionHandler(UserAlreadyExistsException.class)
//    public void handleUserAlreadyExistsException(UserAlreadyExistsException e){
//        log.error("User already exists", e);
//    }
//
//    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="User does not exist")
//    @ExceptionHandler(UserDoesNotExistException.class)
//    public void handleUserDoesNotExistException(UserDoesNotExistException e){
//        log.error("User does not exist", e);
//    }


}
