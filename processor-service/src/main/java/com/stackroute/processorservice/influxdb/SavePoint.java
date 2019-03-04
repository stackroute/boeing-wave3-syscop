//package com.stackroute.processorservice.influxdb;
//
//import org.influxdb.dto.Point;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.concurrent.TimeUnit;
//
//public class SavePoint {
//
//    @Autowired
//    private InfluxDBTemplate<Point> influxDBTemplate;
//
//    public String saveToDatabase() {
//
//        influxDBTemplate.createDatabase();
//        final Point p = Point.measurement("disk")
//                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
//                .tag("tenant", "default")
//                .addField("used", 80L)
//                .addField("free", 1L)
//                .build();
//        influxDBTemplate.write(p);
//        return "saved";
//    }
//}
