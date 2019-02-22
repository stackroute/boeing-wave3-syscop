package com.stackroute.AppRegistration.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    private String applicationName;
    private String applicationType;
    private String ipAddress;
    private Date registrationDateandTime = new Date();
    private List<Service> services;

    public Application(String applicationName, String applicationType, String ipAddress, List<Service> services) {
        this.applicationName = applicationName;
        this.applicationType = applicationType;
        this.ipAddress = ipAddress;
        this.services = services;
    }

    public Application(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationName='" + applicationName + '\'' +
                ", applicationType='" + applicationType + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", services=" + services +
                '}';
    }
}
