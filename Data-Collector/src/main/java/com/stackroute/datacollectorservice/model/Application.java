package com.stackroute.datacollectorservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Application is a domain object class
 * that has applications' name, type(Docker/non-docker),
 * ipaddress of the system, application registration
 * time and date and list of services
 *
 * @author Shri Ramya A
 */

@Data
@Builder
@AllArgsConstructor
public class Application {

    private String applicationName;
    private String applicationType;
    private String ipAddress;
    private Date registrationDateandTime = new Date();
    private List<Services> services;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getRegistrationDateandTime() {
        return registrationDateandTime;
    }

    public void setRegistrationDateandTime(Date registrationDateandTime) {
        this.registrationDateandTime = registrationDateandTime;
    }

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> services) {
        this.services = services;
    }

    public Application(String applicationName, String applicationType, String ipAddress, List<Services> services) {
        this.applicationName = applicationName;
        this.applicationType = applicationType;
        this.ipAddress = ipAddress;
        this.services = services;
    }

    public Application(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Application() {
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationName='" + applicationName + '\'' +
                ", applicationType='" + applicationType + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", registrationDateandTime=" + registrationDateandTime +
                ", services=" + services +
                '}';
    }
}
