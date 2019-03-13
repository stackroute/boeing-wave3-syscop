package com.stackroute.processorservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.processorservice.model.MetricsFinal;
import org.influxdb.dto.QueryResult;

public interface InfluxService {

    public MetricsFinal saveMetricsFinal(MetricsFinal metricsFinal) throws  JsonProcessingException;

    public QueryResult calculateThreshold(String tablename) throws JsonProcessingException;

    public QueryResult calculateAllThreshold() throws JsonProcessingException;

}
