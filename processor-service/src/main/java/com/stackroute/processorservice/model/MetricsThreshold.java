package com.stackroute.processorservice.model;

public class MetricsThreshold {

    private String userName;
    private String serviceName;
    private float cpu;
    private float mem;

    public MetricsThreshold() {
    }

    public MetricsThreshold(String userName, String serviceName, float cpu, float mem) {
        this.userName = userName;
        this.serviceName = serviceName;
        this.cpu = cpu;
        this.mem = mem;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public float getCpu() {
        return cpu;
    }

    public void setCpu(float cpu) {
        this.cpu = cpu;
    }

    public float getMem() {
        return mem;
    }

    public void setMem(float mem) {
        this.mem = mem;
    }


    @Override
    public String toString() {
        return "MetricsThreshold{" +
                "userName='" + userName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", cpu=" + cpu +
                ", mem=" + mem +
                '}';
    }
}
