package com.stackroute.AppRegistration.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

//    @Override
//    public String toString() {
//        return "Service{" +
//                "serviceName='" + serviceName + '\'' +
//                ", portNumber=" + portNumber +
//                '}';
//    }
}
