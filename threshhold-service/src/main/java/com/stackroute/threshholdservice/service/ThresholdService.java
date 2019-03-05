package com.stackroute.threshholdservice.service;

import com.stackroute.threshholdservice.model.MetricsThreshold;

public interface ThresholdService {

    public MetricsThreshold saveMetricsThreshold(MetricsThreshold metricsThreshold);

    public MetricsThreshold getMetricThresholdObj(String serviceName, String userName);
}
