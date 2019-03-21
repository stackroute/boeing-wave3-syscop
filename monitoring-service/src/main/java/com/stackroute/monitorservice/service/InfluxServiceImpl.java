package com.stackroute.monitorservice.service;

import com.stackroute.monitorservice.influxdb.InfluxDBTemplate;
import com.stackroute.monitorservice.model.HistoricalDockerMetric;
import com.stackroute.monitorservice.model.MetricsFinal;
import com.stackroute.monitorservice.model.Range;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class InfluxServiceImpl implements InfluxService {


    private InfluxDBTemplate<Point> influxDBTemplate;

    @Autowired
    public InfluxServiceImpl(InfluxDBTemplate<Point> influxDBTemplate) {
        this.influxDBTemplate = influxDBTemplate;
    }



    public MetricsFinal saveMetrics(MetricsFinal metricsFinal){

        influxDBTemplate.createDatabase();


        final Point p = Point.measurement(metricsFinal.getUserName() + metricsFinal.getServiceName())
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("tenant", "default")
                .addField("username", metricsFinal.getUserName())
                .addField("servicename", metricsFinal.getServiceName())
                .addField("servicetype", metricsFinal.getServiceType())
                .addField("portnumber", metricsFinal.getPortNumber())
                .addField("ConainerId", metricsFinal.getMetrics().getContainerId())
                .addField("containerName", metricsFinal.getMetrics().getContainerName())
                .addField("Cpu", metricsFinal.getMetrics().getCpu())
                .addField("Memory", metricsFinal.getMetrics().getMem())
                .addField("NetI/O", metricsFinal.getMetrics().getNetIO())
                .addField("Block I/O", metricsFinal.getMetrics().getBlockIO())
                .addField("PID", metricsFinal.getMetrics().getpId())
                .build();
        influxDBTemplate.write(p);

        return metricsFinal;

    }

    @Override
    public List<HistoricalDockerMetric> getHistoricalMetrics(Range range) throws ParseException {

        String todateString = range.getToDate();
        String fromdateString = range.getFromDate();
        DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss.SSS z");

        Date toDate1 = dateFormat.parse(todateString);
        Date fromDate1 = dateFormat.parse(fromdateString);

        System.out.println("toDate" + toDate1);
        System.out.println("fromDate" + fromDate1);
        long tounixTime = (long)toDate1.getTime()*1000000;
        long fromunixTime = (long)fromDate1.getTime()*1000000;


        String dbName = "monitorServiceDB";
        Query query1 = new Query("select time, Cpu, Memory from "+range.getTableName()+" where time >= "+fromunixTime + " and time <= "+tounixTime,dbName);

        Query query2 = new Query("select time, Cpu, Memory from "+range.getTableName(),dbName);
        QueryResult queryResult = influxDBTemplate.query(query1);
        QueryResult queryResult2 = influxDBTemplate.query(query2);

        int valSize = queryResult.getResults().get(0).getSeries().get(0).getValues().size();

        List<HistoricalDockerMetric> historicalDockerMetricList = new ArrayList<>();

         for(int i=0;i<valSize;i++) {
             HistoricalDockerMetric historicalDockerMetric = new HistoricalDockerMetric();

             historicalDockerMetric.setTime(queryResult.getResults().get(0).getSeries().get(0).getValues().get(i).get(0).toString());
             historicalDockerMetric.setCpu(queryResult.getResults().get(0).getSeries().get(0).getValues().get(i).get(1).toString());
             historicalDockerMetric.setMem(queryResult.getResults().get(0).getSeries().get(0).getValues().get(i).get(2).toString());
             historicalDockerMetricList.add(historicalDockerMetric);
         }


        return historicalDockerMetricList;
    }


}
