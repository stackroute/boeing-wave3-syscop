package com.syscop.monitoringService.monitoring.service.Model;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Monitor {

    private String containerId;

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

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMem() {
        return mem;
    }

    public void setMem(String mem) {
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

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    private String containerName;
    private String cpu;
    private String mem;
    private String netIO;
    private String blockIO;
    private String pId;

    public Monitor()
    {

    }



}
