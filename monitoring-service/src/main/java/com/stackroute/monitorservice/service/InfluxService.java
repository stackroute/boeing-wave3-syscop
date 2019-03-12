package com.stackroute.monitorservice.service;

import com.stackroute.monitorservice.model.HistoricalDockerMetric;
import com.stackroute.monitorservice.model.MetricsFinal;
import com.stackroute.monitorservice.model.Monitor;
import com.stackroute.monitorservice.model.Range;
import org.influxdb.dto.QueryResult;

import java.text.ParseException;
import java.util.List;

public interface InfluxService {


    public MetricsFinal saveMetrics(MetricsFinal metricsFinal);

    public QueryResult getHistoricalMetrics(Range range) throws ParseException;
}
