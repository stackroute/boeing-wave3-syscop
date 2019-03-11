package com.stackroute.datacollectorservice.MetricModel;


import com.stackroute.datacollectorservice.model.Endpoint;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JavaMetric implements MetricInterface {

    private int totalRequests=0;
    private Endpoint[] endpoints=null;

    @Override
    public void parse(String metricStr) {
        if(metricStr.contains("http_request_duration")){
            String httpReq = metricStr.split("# TYPE http_request_duration summary")[1].split("# HELP sql_queries_total Total number of sql queries")[0];
            String[] httpReqArr = httpReq.split("http_request_duration");
            List<Endpoint> endpointList = new ArrayList<Endpoint>();
            Endpoint tmp;

            for(int i=0; i<httpReqArr.length; i++){
                tmp = new Endpoint();
                httpReqArr[i] = httpReqArr[i].trim();
                tmp.setPath(httpReqArr[i].split("path=\"")[1].split("\"")[0]);
                tmp.setMethod(httpReqArr[i].split("method=\"")[1].split("\"")[0]);
                tmp.setRequestDuration(Integer.getInteger(httpReqArr[i].split(" ")[1]));
            }
        }
    }
}
