package com.stackroute.thresholdservice.repository;

import com.stackroute.thresholdservice.model.MetricsThreshold;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ThresholdRepository extends MongoRepository<MetricsThreshold, String> {

    @Query("{'serviceName' : ?0, '_id' : ?1}")
    public MetricsThreshold getMetricThresholdObject(String serviceName, String userName);
}
