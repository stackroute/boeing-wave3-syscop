package com.stackroute.monitorservice.service;

import com.stackroute.monitorservice.model.Monitor;

public interface InfluxService {


    public Monitor saveMetrics(Monitor monitor);
}
