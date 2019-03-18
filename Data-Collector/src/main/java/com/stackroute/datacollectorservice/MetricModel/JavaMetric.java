package com.stackroute.datacollectorservice.MetricModel;


import com.stackroute.datacollectorservice.model.Endpoint;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
public class JavaMetric implements MetricInterface {

    private String[] path=null;
    private String[] responseTime=null;

//    # Metrics will become visible when they are updated for the first time.
//# HELP http_request_duration Duration for serving the http requests in seconds.
//            # TYPE http_request_duration summary
//    http_request_duration{method="GET",path="/api/v1/tracks",status="404",quantile="0.5",} 0.205382268
//    http_request_duration{method="GET",path="/api/v1/tracks",status="404",quantile="0.9",} 0.205382268
//    http_request_duration{method="GET",path="/api/v1/tracks",status="404",quantile="0.99",} 0.205382268
//    http_request_duration_count{method="GET",path="/api/v1/tracks",status="404",} 1.0
//    http_request_duration_sum{method="GET",path="/api/v1/tracks",status="404",} 0.205382268
//            # HELP http_requests_total Total number of http requests.
//# TYPE http_requests_total counter
//    http_requests_total{method="GET",path="/api/v1/tracks",status="404",} 1.0

    @Override
    public void parse(String metricStr) {
        if(metricStr.contains("http_request_duration")){
            String httpReq = metricStr.split("# TYPE http_request_duration summary")[1].split("# HELP sql_queries_total Total number of sql queries")[0];
            String[] httpReqArr = httpReq.split("\n");
            HashMap<String,String> endpointMap = new HashMap<String,String>();

            for(int i=0; i<httpReqArr.length; i++){
                if(httpReqArr[i].contains("http_request_duration{")){
                    httpReqArr[i] = httpReqArr[i].trim();
                    endpointMap.put(httpReqArr[i].split("path=\"")[1].split("\"")[0],httpReqArr[i].split(" ")[1]);
                }
            }
            path = Arrays.copyOf(endpointMap.keySet().toArray(), endpointMap.keySet().toArray().length, String[].class);
            responseTime = Arrays.copyOf(endpointMap.values().toArray(), endpointMap.values().toArray().length, String[].class);
        }
//        for(int i=0; i<path.length; i++){
////            System.out.println(path[i]+" "+responseTime[i]);
//        }
    }

    @Override
    public String toString() {
        return "{" +
                "path=" + Arrays.toString(path) +
                ", responseTime=" + Arrays.toString(responseTime) +
                '}';
    }
}
