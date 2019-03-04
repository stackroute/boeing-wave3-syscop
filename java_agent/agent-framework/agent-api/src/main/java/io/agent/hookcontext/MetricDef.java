package io.agent.hookcontext;

import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;

import java.util.function.BiFunction;

/**
 * See {@link #MetricDef(String, BiFunction)}
 */
public class MetricDef<T extends Collector> {

    private final String metricName;
    private final BiFunction<String, CollectorRegistry, T> producer;

    /**
     * See {@link MetricsStore}.
     * @param metricName Name of the Prometheus metric, like <tt>"http_requests_total"</tt>.
     * @param producer Function to create a new metric and register it with the registry.
     *                 This function will only be called once, subsequent calls to {@link MetricsStore#createOrGet(MetricDef)}
     *                 will return the previously created metric with the specified name.
     *                 The two parameters are the <i>metricName</i> as specified above,
     *                 and the Prometheus registry where the new metric should be registered.
     *                 For an example see JavaDoc for {@link MetricsStore}.
     */
    public MetricDef(String metricName, BiFunction<String, CollectorRegistry, T> producer) {
        this.metricName = metricName;
        this.producer = producer;
    }

    String getMetricName() {
        return metricName;
    }

    BiFunction<String, CollectorRegistry, T> getProducer() {
        return producer;
    }
}
