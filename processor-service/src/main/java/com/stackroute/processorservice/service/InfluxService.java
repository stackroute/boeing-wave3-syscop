package com.stackroute.processorservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.processorservice.model.Metrics;
import com.stackroute.processorservice.model.MetricsFinal;
import com.stackroute.processorservice.model.Person;
import org.influxdb.dto.QueryResult;

public interface InfluxService {

    public MetricsFinal saveMetricsFinal(MetricsFinal metricsFinal) throws  JsonProcessingException;

    public QueryResult calculateThreshold(String tablename) throws JsonProcessingException;

    public QueryResult calculateAllThreshold() throws JsonProcessingException;

}
