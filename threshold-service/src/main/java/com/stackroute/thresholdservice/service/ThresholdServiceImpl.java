package com.stackroute.thresholdservice.service;

import com.stackroute.thresholdservice.model.MetricsThreshold;
import com.stackroute.thresholdservice.repository.ThresholdRepository;
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

        MetricsThreshold metricsThreshold = new MetricsThreshold();
        metricsThreshold = thresholdRepository.getMetricThresholdObject(serviceName, userName);

        return metricsThreshold;
    }
}
