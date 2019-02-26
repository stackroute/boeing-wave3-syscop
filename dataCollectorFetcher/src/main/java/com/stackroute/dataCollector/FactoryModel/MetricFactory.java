package com.stackroute.dataCollector.FactoryModel;

//import com.anjali.dataCollector.MetricModel.*;
import com.stackroute.dataCollector.MetricModel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MetricFactory {


    private DockerMetric dockerMetric;
    private JavaMetric javaMetric;
    private NodeMetric nodeMetric;
    private PythonMetric pythonMetric;

    @Autowired
    public MetricFactory(DockerMetric dockerMetric, JavaMetric javaMetric, NodeMetric nodeMetric, PythonMetric pythonMetric) {
        this.dockerMetric = dockerMetric;
        this.javaMetric = javaMetric;
        this.nodeMetric = nodeMetric;
        this.pythonMetric = pythonMetric;
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
            case("pythonmetric"):
                object = pythonMetric;
                break;
            default:
                break;
        }
        return object;
    }


}
