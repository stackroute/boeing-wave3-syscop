package com.stackroute.monitorservice.service;

import com.stackroute.monitorservice.model.HistoricalDockerMetric;
import com.stackroute.monitorservice.model.MetricsFinal;
import com.stackroute.monitorservice.model.Range;

import java.text.ParseException;
import java.util.List;

public interface InfluxService {


    public MetricsFinal saveMetrics(MetricsFinal metricsFinal);

    public List<HistoricalDockerMetric> getHistoricalMetrics(Range range) throws ParseException;
}
