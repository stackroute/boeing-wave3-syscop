package com.syscop.monitoringService.monitoring.service.InfluxService;

import com.syscop.monitoringService.monitoring.service.Model.Monitor;

public interface InfluxService {

    public Monitor saveMetrics(Monitor metrics);
}
