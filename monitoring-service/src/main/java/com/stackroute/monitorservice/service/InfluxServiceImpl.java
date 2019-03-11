package com.stackroute.monitorservice.service;

import com.stackroute.monitorservice.influxdb.InfluxDBTemplate;
import com.stackroute.monitorservice.model.MetricsFinal;
import com.stackroute.monitorservice.model.Monitor;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class InfluxServiceImpl implements InfluxService {


    private InfluxDBTemplate<Point> influxDBTemplate;

    @Autowired
    public InfluxServiceImpl(InfluxDBTemplate<Point> influxDBTemplate) {
        this.influxDBTemplate = influxDBTemplate;
    }



    public MetricsFinal saveMetrics(MetricsFinal metricsFinal){

        System.out.println("Saving Metrics");
        influxDBTemplate.createDatabase();


        final Point p = Point.measurement(metricsFinal.getUserName() + metricsFinal.getServiceName())
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("tenant", "default")
                .addField("username", metricsFinal.getUserName())
                .addField("servicename", metricsFinal.getServiceName())
                .addField("servicetype", metricsFinal.getServiceType())
                .addField("portnumber", metricsFinal.getPortNumber())
                .addField("ConainerId", metricsFinal.getMetrics().getContainerId())
                .addField("containerName", metricsFinal.getMetrics().getContainerName())
                .addField("Cpu", metricsFinal.getMetrics().getCpu())
                .addField("Memory", metricsFinal.getMetrics().getMem())
                .addField("NetI/O", metricsFinal.getMetrics().getNetIO())
                .addField("Block I/O", metricsFinal.getMetrics().getBlockIO())
                .addField("PID", metricsFinal.getMetrics().getpId())
                .build();
        influxDBTemplate.write(p);
        System.out.println("Saved Metrics");

        return metricsFinal;

    }


}
