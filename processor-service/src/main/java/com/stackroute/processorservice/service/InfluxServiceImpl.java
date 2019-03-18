package com.stackroute.processorservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.processorservice.influxdb.InfluxDBTemplate;
import com.stackroute.processorservice.model.MetricsFinal;
import com.stackroute.processorservice.model.MetricsThreshold;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class InfluxServiceImpl implements InfluxService {


    private InfluxDBTemplate<Point> influxDBTemplate;
    private static final String TOPIC = "Kafka_Example_Test_Threshold";
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public InfluxServiceImpl(InfluxDBTemplate<Point> influxDBTemplate, KafkaTemplate<String, String> kafkaTemplate) {
        this.influxDBTemplate = influxDBTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }
    public MetricsFinal saveMetricsFinal(MetricsFinal metricsFinal) throws JsonProcessingException {
        String dbName = "mydb";

        int currCount =0;

        Double currD = new Double(0);


        MetricsFinal metricsFinal1 = new MetricsFinal(metricsFinal.getUserName(),
                metricsFinal.getServiceName(),
                metricsFinal.getServiceType(),
                metricsFinal.getPortNumber(),
                metricsFinal.getMetrics());

        try {

            Query query5 = new Query("SELECT count(username) from " + metricsFinal.getUserName() + metricsFinal.getServiceName(), dbName);

            QueryResult queryResult5 = influxDBTemplate.query(query5);

            currD = (double) queryResult5.getResults().get(0).getSeries().get(0).getValues().get(0).get(1);

            currCount = currD.intValue();
        }catch (Exception e){
            //throws exception
        }

        if (currCount <= 30) {
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
        }
        if (currCount == 30) {
            //if sttatement
            String tablename= metricsFinal.getUserName() + metricsFinal.getServiceName();
            QueryResult queryResult = calculateThreshold(tablename);
        }
        return metricsFinal1;
    }
    @Override
    public QueryResult calculateThreshold(String tablename) throws JsonProcessingException {
        String dbName = "mydb";
        String var = tablename;

        Query query1 = new Query("SELECT MEAN(\"Memory\") FROM " + var, dbName);
        Query query2 = new Query("SELECT MEAN(\"Cpu\") FROM " + var, dbName);
        Query query3 = new Query("SELECT username from "+ var +" limit 1;", dbName);
        Query query4 = new Query("SELECT servicename from "+ var +" limit 1;",dbName);

        QueryResult queryResult = influxDBTemplate.query(query1);
        QueryResult queryResult2 = influxDBTemplate.query(query2);
        QueryResult queryResult3 = influxDBTemplate.query(query3);
        QueryResult queryResult4 = influxDBTemplate.query(query4);



        MetricsThreshold metricsThreshold = new MetricsThreshold();

        metricsThreshold.setUserName(queryResult3.getResults().get(0).getSeries().get(0).getValues().get(0).get(1).toString());
        metricsThreshold.setServiceName(queryResult4.getResults().get(0).getSeries().get(0).getValues().get(0).get(1).toString());
        metricsThreshold.setCpu(Float.parseFloat(queryResult2.getResults().get(0).getSeries().get(0).getValues().get(0).get(1).toString()));
        metricsThreshold.setMem(Float.parseFloat(queryResult.getResults().get(0).getSeries().get(0).getValues().get(0).get(1).toString()));


        ObjectMapper obj = new ObjectMapper();
        String metricsThresholdJson = obj.writeValueAsString(metricsThreshold);



        kafkaTemplate.send(TOPIC, metricsThresholdJson);


        return queryResult;
    }
    @Override
    public QueryResult calculateAllThreshold() throws JsonProcessingException {
        String dbName = "mydb";

        Query query0 = new Query("show measurements",dbName);

        QueryResult queryResult0 = influxDBTemplate.query(query0);

        List<String> tableNames = new ArrayList<>();
        int i;
        int j = queryResult0.getResults().get(0).getSeries().get(0).getValues().size();
        for (i=0 ; i<j;i++  ){
            tableNames.add(queryResult0.getResults().get(0).getSeries().get(0).getValues().get(i).toString().replace("[" ,"").replace("]",""));
        }

        Iterator iterator = tableNames.iterator();
        while (iterator.hasNext()){
            String var = iterator.next().toString();


            Query query1 = new Query("SELECT MEAN(\"Memory\") FROM " + var, dbName);
            Query query2 = new Query("SELECT MEAN(\"Cpu\") FROM " + var, dbName);
            Query query3 = new Query("SELECT username from "+ var +" limit 1;", dbName);
            Query query4 = new Query("SELECT servicename from "+ var +" limit 1;",dbName);

            QueryResult queryResult = influxDBTemplate.query(query1);
            QueryResult queryResult2 = influxDBTemplate.query(query2);
            QueryResult queryResult3 = influxDBTemplate.query(query3);
            QueryResult queryResult4 = influxDBTemplate.query(query4);

            MetricsThreshold metricsThreshold = new MetricsThreshold();

            metricsThreshold.setUserName(queryResult3.getResults().get(0).getSeries().get(0).getValues().get(0).get(1).toString());
            metricsThreshold.setServiceName(queryResult4.getResults().get(0).getSeries().get(0).getValues().get(0).get(1).toString());
            metricsThreshold.setCpu(Float.parseFloat(queryResult2.getResults().get(0).getSeries().get(0).getValues().get(0).get(1).toString()));
            metricsThreshold.setMem(Float.parseFloat(queryResult.getResults().get(0).getSeries().get(0).getValues().get(0).get(1).toString()));


            ObjectMapper obj = new ObjectMapper();
            String metricsThresholdJson = obj.writeValueAsString(metricsThreshold);

            kafkaTemplate.send(TOPIC, metricsThresholdJson);
        }
        return queryResult0;
    }


}
