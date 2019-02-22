package com.stackroute.AppRegistration.exceptions;

public class ApplicationAlreadyExistException extends Exception {

    private String message;

    public ApplicationAlreadyExistException(String message){
        super(message);
        this.message = message;
    }
}
