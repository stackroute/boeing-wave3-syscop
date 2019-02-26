package com.stackroute.dataCollector.MetricModel;

import com.stackroute.dataCollector.Model.DataCollectorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DockerMetric implements MetricInterface {


    public String containerId;
    public String containerName;
    public String cpu;
    public String mem;
    public String netId;
    public String blockId;
    public String pId;
    public String port;


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

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Autowired
    DataCollectorModel dataCollectorModel;

    @Override
    public String toString() {
        return "{" +
                "containerId='" + containerId + '\'' +
                ", containerName='" + containerName + '\'' +
                ", cpu='" + cpu + '\'' +
                ", mem='" + mem + '\'' +
                ", netId='" + netId + '\'' +
                ", blockId='" + blockId + '\'' +
                ", pId='" + pId + '\'' +
                ", port='" + port + '\'' +
                ", dataCollectorModel=" + dataCollectorModel +
                '}';
    }

    @Override
    public void parse(String agentType) {

        String[] strMessage = agentType.split (",");


        setContainerId(strMessage[1].split(":")[1].replace("\"",""));
        setContainerName(strMessage[1].split(":")[1].replace("\"",""));
        setCpu(strMessage[4].split(":")[1].replace("\"",""));
        setMem(strMessage[2].split(":")[1].replace("\"",""));
        setNetId(strMessage[3].split(":")[1].replace("\"",""));
        setBlockId (strMessage[5].split(":")[1].replace("\"",""));
        setpId (strMessage[6].split(":")[1].replace("\"",""));

        System.out.println (this.toString ());

    }

}





