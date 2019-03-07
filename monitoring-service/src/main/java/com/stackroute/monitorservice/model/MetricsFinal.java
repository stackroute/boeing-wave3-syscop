package com.stackroute.monitorservice.model;


import org.springframework.stereotype.Component;

@Component
public class MetricsFinal {


    private String userName;
    private String serviceName;
    private String serviceType;
    private int portNumber;
    private Metrics metrics;

    public MetricsFinal() {
    }

    public MetricsFinal(String userName, String serviceName, String serviceType, int portNumber, Metrics metrics) {
        this.userName = userName;
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.portNumber = portNumber;
        this.metrics = metrics;
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    @Override
    public String toString() {
        return "MetricsFinal{" +
                "userName='" + userName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", portNumber=" + portNumber +
                ", metrics=" + metrics +
                '}';
    }
}


