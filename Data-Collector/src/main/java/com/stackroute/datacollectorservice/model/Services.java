package com.stackroute.datacollectorservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 *Services is a domain object class
 * that stores a services' name, type(java/python/node) and
 * port on which the service is running
 * in the system
 *
 * @author Shri Ramya A
 */

@Builder
@Data
@AllArgsConstructor
public class Services {

    private String serviceName;
    private String serviceType;
    private int portNumber;

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

    public Services(String serviceName, int portNumber) {
        this.serviceName = serviceName;
        this.portNumber = portNumber;
    }

    public Services() {
    }
}
