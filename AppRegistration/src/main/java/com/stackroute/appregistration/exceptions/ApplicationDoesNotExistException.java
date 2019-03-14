package com.stackroute.appregistration.exceptions;

/**
 * Custom Exception class thrown when we try to
 * retrieve non-existing application
 */
public class ApplicationDoesNotExistException extends Exception{

    private String message;

    public ApplicationDoesNotExistException(String message){
       super(message);
        this.message = message;
    }
}
