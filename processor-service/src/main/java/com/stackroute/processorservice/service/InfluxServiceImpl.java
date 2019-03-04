package com.stackroute.processorservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stackroute.processorservice.influxdb.InfluxDBTemplate;
import com.stackroute.processorservice.model.Metrics;
import com.stackroute.processorservice.model.MetricsFinal;
import com.stackroute.processorservice.model.MetricsThreshold;
import com.stackroute.processorservice.model.Person;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class InfluxServiceImpl implements InfluxService {


    private InfluxDBTemplate<Point> influxDBTemplate;

//    @Autowired
//    public InfluxServiceImpl(InfluxDBTemplate<Point> influxDBTemplate) {
//        this.influxDBTemplate = influxDBTemplate;
//    }



    private static final String TOPIC = "Kafka_Example_Test_Threshold";

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public InfluxServiceImpl(InfluxDBTemplate<Point> influxDBTemplate, KafkaTemplate<String, String> kafkaTemplate) {
        this.influxDBTemplate = influxDBTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Person savePerson(Person person) {

        System.out.println("Saving Person");
        influxDBTemplate.createDatabase();

        Person person1 =  new Person(person.getName(), person.getAge(), person.getGender());
        final Point p = Point.measurement("disk")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("tenant", "default")
                .addField("used", 80L)
                .addField("free", 1L)
                .addField("name", person.getName())
                .addField("age", person.getAge())
                .addField("gender", person.getGender())
                .build();
        influxDBTemplate.write(p);
        System.out.println("Saved Person");
        return person1;
    }

//    public Metrics saveMetrics(Metrics metrics){
//
//        System.out.println("Saving Metrics");
//        influxDBTemplate.createDatabase();
//
//        Metrics metrics1 =  new Metrics(metrics.getBlockIO(),
//                                        metrics.getContainerId(),
//                                        metrics.getContainerName(),
//                                        metrics.getCpu(),
//                                        metrics.getMem(),
//                                        metrics.getNetIO(),
//                                        metrics.getpId());
//        final Point p = Point.measurement("dockerMetrics")
//                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
//                .tag("tenant", "default")
//                .addField("used", 80L)
//                .addField("free", 1L)
//                .addField("ConainerId", metrics.getContainerId())
//                .addField("containerName", metrics.getContainerName())
//                .addField("Cpu", metrics.getCpu())
//                .addField("Memory", metrics.getMem())
//                .addField("NetI/O", metrics.getNetIO())
//                .addField("Block I/O", metrics.getBlockIO())
//                .addField("PID", metrics.getpId())
//                .build();
//        influxDBTemplate.write(p);
//        System.out.println("Saved Metrics");
//        return metrics1;
//
//
//    }

    public MetricsFinal saveMetricsFinal(MetricsFinal metricsFinal){

        System.out.println("Saving Metrics");
        influxDBTemplate.createDatabase();

        MetricsFinal metricsFinal1 =  new MetricsFinal(metricsFinal.getUserName(),
                                                       metricsFinal.getServiceName(),
                                                        metricsFinal.getServiceType(),
                                                        metricsFinal.getPortNumber(),
                                                        metricsFinal.getMetrics());


        final Point p = Point.measurement(metricsFinal.getServiceName())
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
        return metricsFinal1;


    }

    @Override
    public QueryResult calculateThreshold() throws JsonProcessingException {



        String dbName = "mydb";

        Query query0 = new Query("show measurements",dbName);

//        Query query1 = new Query("SELECT MEAN(\"Memory\") FROM \"syscopService3\"\n", dbName);
//        Query query2 = new Query("SELECT MEAN(\"Cpu\") FROM \"syscopService3\"\n", dbName);
//        Query query3 = new Query("SELECT username from syscopService3 limit 1;", dbName);

        QueryResult queryResult0 = influxDBTemplate.query(query0);
//        QueryResult queryResult = influxDBTemplate.query(query1);
//        QueryResult queryResult2 = influxDBTemplate.query(query2);
//        QueryResult queryResult3 = influxDBTemplate.query(query3);

//        System.out.println(queryResult0);
//        System.out.println(queryResult0.getResults().get(0).getSeries().get(0).getValues().get(0).toString().replace("[" ,"").replace("]",""));
//        System.out.println(queryResult0.getResults().get(0).getSeries().get(0).getValues().size());

        List<String> tableNames = new ArrayList<>();
        int i;
        int j = queryResult0.getResults().get(0).getSeries().get(0).getValues().size();
        for (i=0 ; i<j;i++  ){
            tableNames.add(queryResult0.getResults().get(0).getSeries().get(0).getValues().get(i).toString().replace("[" ,"").replace("]",""));
        }

        Iterator iterator = tableNames.iterator();
        while (iterator.hasNext()){
            System.out.println("--------");


            String var = iterator.next().toString();



//            Query query1 = new Query("SELECT MEAN(\"Memory\") FROM \"syscopService3\"\n", dbName);
//            Query query2 = new Query("SELECT MEAN(\"Cpu\") FROM \"syscopService3\"\n", dbName);
//            Query query3 = new Query("SELECT username from syscopService3 limit 1;", dbName);

            Query query1 = new Query("SELECT MEAN(\"Memory\") FROM " + var, dbName);
            Query query2 = new Query("SELECT MEAN(\"Cpu\") FROM " + var, dbName);
            Query query3 = new Query("SELECT username from "+ var +" limit 1;", dbName);

            QueryResult queryResult = influxDBTemplate.query(query1);
            QueryResult queryResult2 = influxDBTemplate.query(query2);
            QueryResult queryResult3 = influxDBTemplate.query(query3);


            System.out.println(var);
            System.out.println("!!!!!!!!!!!!!!!!!!Memory");
            System.out.println(queryResult.getResults().get(0).getSeries().get(0).getValues().get(0).get(1));
            System.out.println("!!!!!!!!!!!!!!!!!!Cpu");
            System.out.println(queryResult2.getResults().get(0).getSeries().get(0).getValues().get(0).get(1));
            System.out.println("!!!!!!!!!!!!!!!!!!Username");
            System.out.println(queryResult3.getResults().get(0).getSeries().get(0).getValues().get(0).get(1));


            MetricsThreshold metricsThreshold = new MetricsThreshold();

            metricsThreshold.setUserName(queryResult3.getResults().get(0).getSeries().get(0).getValues().get(0).get(1).toString());
            metricsThreshold.setServiceName(var);
            metricsThreshold.setCpu(Float.parseFloat(queryResult2.getResults().get(0).getSeries().get(0).getValues().get(0).get(1).toString()));
            metricsThreshold.setMem(Float.parseFloat(queryResult.getResults().get(0).getSeries().get(0).getValues().get(0).get(1).toString()));

            System.out.println(metricsThreshold.toString());

            ObjectMapper obj = new ObjectMapper();
            String metricsThresholdJson = obj.writeValueAsString(metricsThreshold);


            System.out.println(metricsThresholdJson);

            kafkaTemplate.send(TOPIC, metricsThresholdJson);


        }




        return queryResult0;
    }


}
