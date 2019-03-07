package com.stackroute.datacollectorservice.FactoryModel;

import com.stackroute.datacollectorservice.MetricModel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MetricFactory {

    private DockerMetric dockerMetric;
    private JavaMetric javaMetric;
    private NodeMetric nodeMetric;


    @Autowired
    public MetricFactory(DockerMetric dockerMetric, JavaMetric javaMetric, NodeMetric nodeMetric) {
        this.dockerMetric = dockerMetric;
        this.javaMetric = javaMetric;
        this.nodeMetric = nodeMetric;

    }

    public MetricInterface createObject(String metricName){
        MetricInterface object = null;
        switch(metricName.toLowerCase ()){
            case("dockermetric"):
                object = dockerMetric;
                break;
            case("javametric"):
                object = javaMetric;
                break;
            case("nodemetric"):
                object = nodeMetric;
                break;
            default:
                break;
        }
        return object;
    }


}
