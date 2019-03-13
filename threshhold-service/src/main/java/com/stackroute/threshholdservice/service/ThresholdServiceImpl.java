package com.stackroute.threshholdservice.service;

import com.stackroute.threshholdservice.model.MetricsThreshold;
import com.stackroute.threshholdservice.repository.ThresholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThresholdServiceImpl implements ThresholdService {

    private ThresholdRepository thresholdRepository;

    @Autowired
    public ThresholdServiceImpl(ThresholdRepository thresholdRepository) {
        this.thresholdRepository = thresholdRepository;
    }

    @Override
    public MetricsThreshold saveMetricsThreshold(MetricsThreshold metricsThreshold) {

        thresholdRepository.save(metricsThreshold);

        return metricsThreshold;
    }

    @Override
    public MetricsThreshold getMetricThresholdObj(String serviceName, String userName) {

        MetricsThreshold metricsThreshold;
        metricsThreshold = thresholdRepository.getMetricThresholdObject(serviceName, userName);

        return metricsThreshold;
    }
}
