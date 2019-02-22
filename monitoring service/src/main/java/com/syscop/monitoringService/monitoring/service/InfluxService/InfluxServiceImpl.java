package com.syscop.monitoringService.monitoring.service.InfluxService;

import com.syscop.monitoringService.monitoring.service.Model.Monitor;
import com.syscop.monitoringService.monitoring.service.influxdb.InfluxDBTemplate;
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

    @Override
    public Monitor saveMetrics(Monitor monitor){

        System.out.println("Saving Metrics");
        influxDBTemplate.createDatabase();

        Monitor monitor1 = new Monitor();
                monitor1.saveMonitor(monitor.getBlockIO(),
                monitor.getContainerId(),
                monitor.getContainerName(),
                monitor.getCpu(),
                monitor.getMem(),
                monitor.getNetIO(),
                monitor.getpId());
        final Point p = Point.measurement("dockerMetrics")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("tenant", "default")
                .addField("used", 80L)
                .addField("free", 1L)
                .addField("ConainerId", monitor.getContainerId())
                .addField("containerName", monitor.getContainerName())
                .addField("Cpu", monitor.getCpu())
                .addField("Memory", monitor.getMem())
                .addField("NetI/O", monitor.getNetIO())
                .addField("Block I/O", monitor.getBlockIO())
                .addField("PID", monitor.getpId())
                .build();
        influxDBTemplate.write(p);
        System.out.println("Saved Monitor");
        return monitor1;
        
    }


}
