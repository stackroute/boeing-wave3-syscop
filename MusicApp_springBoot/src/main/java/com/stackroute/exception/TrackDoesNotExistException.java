package com.stackroute.exception;

public class TrackDoesNotExistException extends Exception{

    private String message;

    public TrackDoesNotExistException() {
    }

    public TrackDoesNotExistException(String message) {
        super(message);
        this.message = message;
    }
}
