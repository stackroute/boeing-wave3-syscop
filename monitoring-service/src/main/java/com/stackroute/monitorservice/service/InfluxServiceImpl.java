package com.stackroute.monitorservice.service;

import com.stackroute.monitorservice.influxdb.InfluxDBTemplate;
import com.stackroute.monitorservice.model.HistoricalDockerMetric;
import com.stackroute.monitorservice.model.MetricsFinal;
import com.stackroute.monitorservice.model.Monitor;
import com.stackroute.monitorservice.model.Range;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        System.out.println("Saving Metrics");
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
        System.out.println("Saved Metrics");

        return metricsFinal;

    }

    @Override
    public QueryResult getHistoricalMetrics(Range range) throws ParseException {

        String todateString = range.getToDate();
        String fromdateString = range.getFromDate();
        DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss.SSS z");
//        DateFormat fromDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z");

        Date toDate1 = dateFormat.parse(todateString);
        Date fromDate1 = dateFormat.parse(fromdateString);
        long tounixTime = (long)toDate1.getTime()*1000000;
        long fromunixTime = (long)fromDate1.getTime()*1000000;
        System.out.println(tounixTime);
        System.out.println(fromunixTime);


        String dbName = "monitorServiceDB";
        System.out.println("TableName" + range.getTableName());
        Query query1 = new Query("select time, Cpu, Memory from "+range.getTableName()+" where time >= "+tounixTime + " and time <= "+fromunixTime,dbName);

        Query query2 = new Query("select time, Cpu, Memory from "+range.getTableName(),dbName);
        QueryResult queryResult = influxDBTemplate.query(query1);
        QueryResult queryResult2 = influxDBTemplate.query(query2);

        System.out.println(queryResult);
        System.out.println(queryResult2);

        System.out.println("!!@@11111"+queryResult.getResults().get(0));
        System.out.println("!!@@22222" + queryResult.getResults().get(0).getSeries());
        System.out.println("!!@@33333" + queryResult.getResults().get(0).getSeries().get(0));


        return queryResult;
        //<- prints 1352504418
    }


}
