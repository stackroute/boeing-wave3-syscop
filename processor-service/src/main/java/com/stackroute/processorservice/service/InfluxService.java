package com.stackroute.processorservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.processorservice.model.Metrics;
import com.stackroute.processorservice.model.MetricsFinal;
import com.stackroute.processorservice.model.Person;
import org.influxdb.dto.QueryResult;

public interface InfluxService {

    public Person savePerson(Person person);

    //public Metrics saveMetrics(Metrics metrics);

    public MetricsFinal saveMetricsFinal(MetricsFinal metricsFinal);

    public QueryResult calculateThreshold() throws JsonProcessingException;
}
