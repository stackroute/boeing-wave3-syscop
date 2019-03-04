package io.agent.it;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WildflyIT {

    /**
     * Run some HTTP queries against a Docker container from image agent/wildfly-kitchensink-agent.
     * <p/>
     * The Docker container is started by the maven-docker-plugin when running <tt>mvn verify -Pwildfly</tt>.
     */
    @Test
    public void testWildfly() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request restRequest = new Request.Builder().url(System.getProperty("deployment.url") + "/rest/members").build();

        // Execute REST call
        Response restResponse = client.newCall(restRequest).execute();
        Assertions.assertEquals(restResponse.code(), 200);
        Assertions.assertTrue(restResponse.body().string().contains("John Smith"));

        Thread.sleep(100); // metric is incremented after servlet has written the response, wait a little to get the updated metric
        assertMetrics(client, "1.0");

        // Execute REST call again
        restResponse = client.newCall(restRequest).execute();
        Assertions.assertEquals(restResponse.code(), 200);
        Assertions.assertTrue(restResponse.body().string().contains("John Smith"));

        Thread.sleep(100); // metric is incremented after servlet has written the response, wait a little to get the updated metric
        assertMetrics(client, "2.0");
    }

    private void assertMetrics(OkHttpClient client, String nCalls) throws Exception {

        Request metricsRequest = new Request.Builder().url(System.getProperty("agent.url") + "/metrics").build();
        Response metricsResponse = client.newCall(metricsRequest).execute();
        String[] metricsLines = metricsResponse.body().string().split("\n");

        String httpRequestsTotal = Arrays.stream(metricsLines)
                .filter(m -> m.contains("http_requests_total"))
                .filter(m -> m.contains("method=\"GET\""))
                .filter(m -> m.contains("path=\"/wildfly-kitchensink/rest/members\""))
                .filter(m -> m.contains("status=\"200\""))
                .findFirst().orElseThrow(() -> new Exception("http_requests_total metric not found."));

        assertTrue(httpRequestsTotal.endsWith(nCalls), "Value should be " + nCalls + " for " + httpRequestsTotal);

        String sqlQueriesTotal = Arrays.stream(metricsLines)
                .filter(m -> m.contains("sql_queries_total"))
                .filter(m -> m.matches(".*?query=\"select .*?id .*?email .*?name .*?phone_number .*? from Member .*?\".*?"))
                .filter(m -> m.contains("method=\"GET\""))
                .filter(m -> m.contains("path=\"/wildfly-kitchensink/rest/members\""))
                .findFirst().orElseThrow(() -> new Exception("sql_queries_total metric not found."));

        assertTrue(sqlQueriesTotal.endsWith(nCalls), "Value should be " + nCalls + " for " + sqlQueriesTotal);
    }
}
