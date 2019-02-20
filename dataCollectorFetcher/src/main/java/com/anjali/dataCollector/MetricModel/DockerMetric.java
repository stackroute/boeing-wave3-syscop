package com.anjali.dataCollector.MetricModel;

import com.anjali.dataCollector.Model.DataCollectorModel;
import org.springframework.beans.factory.annotation.Autowired;

public class DockerMetric implements MetricInterface {


    @Autowired
    DataCollectorModel dataCollectorModel;

    @Override
    public void parse(String agentType) {



    }
}
