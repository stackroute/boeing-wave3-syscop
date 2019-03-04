package io.agent.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

public class SpringIT {

    /**
     * Run some HTTP queries against a Docker container from image agent/spring-agent.
     * <p/>
     * The Docker container is started by the maven-docker-plugin when running <tt>mvn verify -Pspring</tt>.
     */
    @Test
    public void testSpring() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request metricsRequest = new Request.Builder().url(System.getProperty("agent.url") + "/metrics").build();

        // Execute two POST requests
        Response restResponse = client.newCall(makePostRequest("Frodo", "Baggins")).execute();
        assertEquals(201, restResponse.code());
        restResponse = client.newCall(makePostRequest("Bilbo", "Baggins")).execute();
        assertEquals(201, restResponse.code());

        // Query Prometheus metrics from agent
        Response metricsResponse = client.newCall(metricsRequest).execute();
        String[] metricsLines = metricsResponse.body().string().split("\n");

        String httpRequestsTotal = Arrays.stream(metricsLines)
                .filter(m -> m.contains("http_requests_total"))
                .filter(m -> m.contains("method=\"POST\""))
                .filter(m -> m.contains("path=\"/people\""))
                .filter(m -> m.contains("status=\"201\""))
                .findFirst().orElseThrow(() -> new Exception("http_requests_total metric not found."));

        assertTrue(httpRequestsTotal.endsWith("2.0"), "Value should be 2.0 for " + httpRequestsTotal);

        String sqlQueriesTotal = Arrays.stream(metricsLines)
                .filter(m -> m.contains("sql_queries_total"))
                // The following regular expression tests for this string, but allows the parameters 'id', 'fist_name', 'last_name' to change order:
                // query="insert into person (first_name, last_name, id)"
                .filter(m -> m.matches(".*query=\"insert into person \\((?=.*id)(?=.*first_name)(?=.*last_name).*\\) values \\(...\\)\".*"))
                .filter(m -> m.contains("method=\"POST\""))
                .filter(m -> m.contains("path=\"/people\""))
                .findFirst().orElseThrow(() -> new Exception("sql_queries_total metric not found."));

        assertTrue(sqlQueriesTotal.endsWith("2.0"), "Value should be 2.0 for " + sqlQueriesTotal);
    }

    private Request makePostRequest(String firstName, String lastName) {
        return new Request.Builder()
                .url(System.getProperty("deployment.url") + "/people")
                .method("POST", RequestBody.create(
                        MediaType.parse("application/json"),
                        "{  \"firstName\" : \"" + firstName + "\",  \"lastName\" : \"" + lastName + "\" }"))
                .build();
    }
}
