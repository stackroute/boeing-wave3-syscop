

package io.agent.internal;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;

import java.io.IOException;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.util.Collections;

/**
 * Use the Java runtime's built-in {@link HttpServer} to export Prometheus metrics.
 */
class BuiltInServer {

    static void run(String host, String portString, CollectorRegistry registry) throws Exception {
        try {
            int port = Integer.parseInt(portString);
            InetSocketAddress address = host == null ? new InetSocketAddress(port) : new InetSocketAddress(host, port);
            HttpServer httpServer = HttpServer.create(address, 10);
            httpServer.createContext("/", httpExchange -> {
                if ("/metrics".equals(httpExchange.getRequestURI().getPath())) {
                    respondMetrics(registry, httpExchange);
                } else {
                    respondRedirect(httpExchange);
                }
            });
            httpServer.start();
        } catch (NumberFormatException e) {
            throw new RuntimeException("Failed to parse command line arguments: '" + portString + "' is not a valid port number.");
        }
    }

    private static void respondMetrics(CollectorRegistry registry, HttpExchange httpExchange) throws IOException {
        StringWriter respBodyWriter = new StringWriter();
        respBodyWriter.write("# Metrics will become visible when they are updated for the first time.\n");
        TextFormat.write004(respBodyWriter, registry.metricFamilySamples());
        byte[] respBody = respBodyWriter.toString().getBytes("UTF-8");
        httpExchange.getResponseHeaders().put("Context-Type", Collections.singletonList("text/plain; charset=UTF-8"));
        httpExchange.sendResponseHeaders(200, respBody.length);
        httpExchange.getResponseBody().write(respBody);
        httpExchange.getResponseBody().close();
    }

    private static void respondRedirect(HttpExchange httpExchange) throws IOException {
        byte[] respBody = "Metrics are provided on the /metrics endpoint.".getBytes("UTF-8");
        httpExchange.getResponseHeaders().add("Location", "/metrics");
        httpExchange.getResponseHeaders().put("Context-Type", Collections.singletonList("text/plain; charset=UTF-8"));
        httpExchange.sendResponseHeaders(302, respBody.length);
        httpExchange.getResponseBody().write(respBody);
        httpExchange.getResponseBody().close();
    }
}
