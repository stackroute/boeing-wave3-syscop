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

        return null;


    }


}
