package com.stackroute.monitorservice.model;

public class GraphMetrics {

    private String userName;
    private String serviceName;
    private String cpu;
    private String memory;
    private String netIO;

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

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getNetIO() {
        return netIO;
    }

    public void setNetIO(String netIO) {
        this.netIO = netIO;
    }


    public GraphMetrics(String userName, String serviceName, String cpu, String memory, String netIO) {
        this.userName = userName;
        this.serviceName = serviceName;
        this.cpu = cpu;
        this.memory = memory;
        this.netIO = netIO;
    }


    public GraphMetrics() {
    }
}
