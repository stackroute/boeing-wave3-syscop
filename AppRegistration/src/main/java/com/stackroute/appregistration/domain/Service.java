package com.stackroute.appregistration.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *service is a domain object class
 * that stores a services' name, type(java/python/node) and
 * port on which the service is running
 * in the system
 *
 * @author Shri Ramya A
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {

    private String serviceName;
    private String serviceType;
    private int portNumber;

    public Service(String serviceName, int portNumber) {
        this.serviceName = serviceName;
        this.portNumber = portNumber;
    }

}
