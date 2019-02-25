package com.stackroute.dockeragent.domain;

public class Metrics {

    private String containerId;
    private String containerName;
    private String cpu;
    private String mem;
    private String netIO;
    private String blockIO;
    private String pId;
    private String port;



    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getNetIO() {
        return netIO;
    }

    public void setNetIO(String netIO) {
        this.netIO = netIO;
    }

    public String getBlockIO() {
        return blockIO;
    }

    public void setBlockIO(String blockIO) {
        this.blockIO = blockIO;
    }


    public String getPort() {


        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Metrics() {
    }

    public String getpId() {
        return this.pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getCpu() {
        return this.cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMem() {
        return this.mem;
    }

    public void setMem(String mem) {
        this.mem = mem;
    }


//    @Override
//    public String toString() {
//        return "Metrics{" +
//                "containerId='" + containerId + '\'' +
//                ", containerName='" + containerName + '\'' +
//                ", cpu='" + cpu + '\'' +
//                ", mem='" + mem + '\'' +
//                ", netIO='" + netIO + '\'' +
//                ", blockIO='" + blockIO + '\'' +
//                ", pId='" + pId + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "Metrics{" +
                "containerId='" + containerId + '\'' +
                ", containerName='" + containerName + '\'' +
                ", cpu='" + cpu + '\'' +
                ", mem='" + mem + '\'' +
                ", netIO='" + netIO + '\'' +
                ", blockIO='" + blockIO + '\'' +
                ", pId='" + pId + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
