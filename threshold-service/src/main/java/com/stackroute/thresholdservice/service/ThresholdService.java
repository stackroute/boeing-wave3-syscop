package com.stackroute.thresholdservice.service;

import com.stackroute.thresholdservice.model.MetricsThreshold;

public interface ThresholdService {

    public MetricsThreshold saveMetricsThreshold(MetricsThreshold metricsThreshold);

    public MetricsThreshold getMetricThresholdObj(String serviceName, String userName);
}
