package io.agent.internal.jmx;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;

import java.io.IOException;
import java.io.StringWriter;

public class Exporter implements ExporterMBean {

    private final CollectorRegistry registry;

    public Exporter(CollectorRegistry registry) {
        this.registry = registry;
    }

    @Override
    public String getTextFormat() {
        try {
            StringWriter result = new StringWriter();
            TextFormat.write004(result, registry.metricFamilySamples());
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException("Unexpected error when writing metrics to a String: " + e.getMessage(), e);
        }
    }
}
