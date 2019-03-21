package com.stackroute.threshholdservice.repository;

import com.stackroute.threshholdservice.model.MetricsThreshold;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ThresholdRepository extends MongoRepository<MetricsThreshold, String> {

    @Query("{'serviceName' : ?0, 'userName' : ?1}")
    public MetricsThreshold getMetricThresholdObject(String serviceName, String userName);
}
