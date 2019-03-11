package com.stackroute.monitorservice.service;

import com.stackroute.monitorservice.model.MetricsFinal;
import com.stackroute.monitorservice.model.Monitor;

public interface InfluxService {


    public MetricsFinal saveMetrics(MetricsFinal metricsFinal);
}
