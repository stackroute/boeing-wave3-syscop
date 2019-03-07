package com.stackroute.datacollectorservice.model;

import org.springframework.stereotype.Component;

@Component
public class ServiceFields {

    private String userName;
    private String appplicationType;
    private String applicationIP;
    private String serviceName;
    private String serviceType;
    private String servicePort;
    private String agentUrl;


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

    public String getServicePort() {
        return servicePort;
    }

    public void setServicePort(String servicePort) {
        this.servicePort = servicePort;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAppplicationType() {
        return appplicationType;
    }

    public void setAppplicationType(String appplicationType) {
        this.appplicationType = appplicationType;
    }

    public String getApplicationIP() {
        return applicationIP;
    }

    public void setApplicationIP(String applicationIP) {
        this.applicationIP = applicationIP;
    }

    @Override
    public String toString() {
        return "ServiceFields{" +
                "userName='" + userName + '\'' +
                ", appplicationType='" + appplicationType + '\'' +
                ", applicationIP='" + applicationIP + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", servicePort='" + servicePort + '\'' +
                ", agentUrl='" + agentUrl + '\'' +
                '}';
    }

    public String getAgentUrl() {
        return agentUrl;
    }

    public void setAgentUrl(String agentUrl) {
        this.agentUrl = agentUrl;
    }

    public ServiceFields() {
    }

    public ServiceFields(String serviceName, String serviceType, String servicePort) {
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.servicePort = servicePort;
    }
}
