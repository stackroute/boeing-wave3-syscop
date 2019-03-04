package com.stackroute.processorservice.service;

import com.stackroute.processorservice.model.Metrics;
import com.stackroute.processorservice.model.Person;


/*Interface for service
 * to save person and metrics data in InfluxDB
 */
public interface InfluxService {

    public Person savePerson(Person person);

    public Metrics saveMetrics(Metrics metrics);
}
