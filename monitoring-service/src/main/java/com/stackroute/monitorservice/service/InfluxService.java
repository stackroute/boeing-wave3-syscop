package com.stackroute.monitorservice.service;

import com.stackroute.monitorservice.model.HistoricalDockerMetric;
import com.stackroute.monitorservice.model.MetricsFinal;
import com.stackroute.monitorservice.model.Monitor;

import java.text.ParseException;
import java.util.List;

public interface InfluxService {


    public MetricsFinal saveMetrics(MetricsFinal metricsFinal);

    public List<HistoricalDockerMetric> getHistoricalMetrics(String toDate, String fromDate) throws ParseException;
}
