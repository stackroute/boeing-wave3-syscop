package com.stackroute.datacollectorservice.MetricModel;

import com.stackroute.datacollectorservice.model.DataCollectorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


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
    public String dockMetric = "";


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
                //", port='" + port + '\'' +
                '}';
    }

    @Override
    public void parse(String agentType) {

         String str = agentType;
         String strMetric = "";
        List<String> docMet = new ArrayList<String> ();

        while(str.indexOf ('{')!=-1){
            strMetric = str.substring (str.indexOf ('{'), str.indexOf ('}')+1);
            str = str.substring (str.indexOf ('}')+1, str.length ());
            docMet.add (strMetric);
        }


    }

}





