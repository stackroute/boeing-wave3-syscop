package com.stackroute.datacollectorservice.model;

public class Endpoint {
    private int requestDuration;
    private String path;
    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getRequestDuration() {
        return requestDuration;
    }

    public void setRequestDuration(int requestDuration) {
        this.requestDuration = requestDuration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
