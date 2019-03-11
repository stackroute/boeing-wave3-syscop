package com.stackroute.monitorservice.model;

import org.springframework.stereotype.Component;


@Component
public class Metrics {

    private String containerId;
    private String containerName;
    private float cpu;
    private float mem;
    private String netIO;
    private String blockIO;
    private long pId;

    public Metrics(String containerId, String containerName, float cpu, float mem, String netIO, String blockIO, long pId) {
        this.containerId = containerId;
        this.containerName = containerName;
        this.cpu = cpu;
        this.mem = mem;
        this.netIO = netIO;
        this.blockIO = blockIO;
        this.pId = pId;
    }

    public Metrics() {
    }

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

    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return "Metrics{" +
                "containerId='" + containerId + '\'' +
                ", containerName='" + containerName + '\'' +
                ", cpu=" + cpu +
                ", mem=" + mem +
                ", netIO='" + netIO + '\'' +
                ", blockIO='" + blockIO + '\'' +
                ", pId=" + pId +
                '}';
    }
}
