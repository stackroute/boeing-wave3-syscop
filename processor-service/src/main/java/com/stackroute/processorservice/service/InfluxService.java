package com.stackroute.processorservice.service;

import com.stackroute.processorservice.model.Metrics;
import com.stackroute.processorservice.model.Person;

public interface InfluxService {

    public Person savePerson(Person person);

    public Metrics saveMetrics(Metrics metrics);
}
