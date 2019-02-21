package com.stackroute.processorservice.service;

import com.stackroute.processorservice.influxdb.InfluxDBTemplate;
import com.stackroute.processorservice.model.Metrics;
import com.stackroute.processorservice.model.Person;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/*Service implementation for dtoring data in influxDB*/
@Service
public class InfluxServiceImpl implements InfluxService {


    private InfluxDBTemplate<Point> influxDBTemplate;

    @Autowired
    public InfluxServiceImpl(InfluxDBTemplate<Point> influxDBTemplate) {
        this.influxDBTemplate = influxDBTemplate;
    }

    @Override


    //Stores a Person data in influxDB
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
    //Stores metrics data in the InfluxDB
    public Metrics saveMetrics(Metrics metrics){

        System.out.println("Saving Metrics");
        influxDBTemplate.createDatabase();

        Metrics metrics1 =  new Metrics(metrics.getBlockIO(),
                                        metrics.getContainerId(),
                                        metrics.getContainerName(),
                                        metrics.getCpu(),
                                        metrics.getMem(),
                                        metrics.getNetIO(),
                                        metrics.getpId());
        final Point p = Point.measurement("dockerMetrics")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("tenant", "default")
                .addField("used", 80L)
                .addField("free", 1L)
                .addField("ConainerId", metrics.getContainerId())
                .addField("containerName", metrics.getContainerName())
                .addField("Cpu", metrics.getCpu())
                .addField("Memory", metrics.getMem())
                .addField("NetI/O", metrics.getNetIO())
                .addField("Block I/O", metrics.getBlockIO())
                .addField("PID", metrics.getpId())
                .build();
        influxDBTemplate.write(p);
        System.out.println("Saved Metrics");
        return metrics1;


    }


}
