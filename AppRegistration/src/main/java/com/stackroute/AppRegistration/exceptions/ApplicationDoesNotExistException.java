package com.stackroute.AppRegistration.exceptions;

public class ApplicationDoesNotExistException extends Exception{

    private String message;

    public ApplicationDoesNotExistException(String message){
       super(message);
        this.message = message;
    }
}
