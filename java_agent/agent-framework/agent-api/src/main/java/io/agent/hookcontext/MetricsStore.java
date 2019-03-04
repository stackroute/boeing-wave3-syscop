package io.agent.hookcontext;

import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;

/**
 * Instead of creating Prometheus Metrics directly, Hooks should use the {@link MetricsStore} like this:
 * <pre>
 * Counter httpRequestsTotal = metricsStore.createOrGet(new MetricDef<>(
 *         "http_requests_total",
 *         (name, registry) -> Counter.build()
 *                 .name(name)
 *                 .labelNames("method", "path", "status")
 *                 .help("Total number of http requests.")
 *                 .register(registry)
 * ));
 * </pre>
 * The Promgent framework will take care that each metric is created only once and re-used across re-deployments in an application server.
 */
public class MetricsStore {

    private final CollectorRegistry registry;
    private final ConcurrentMap<String, Collector> metrics = new ConcurrentHashMap<>();

    public MetricsStore(CollectorRegistry registry) {
        this.registry = registry;
    }

     // See {@link MetricsStore} and {@link MetricDef#MetricDef(String, BiFunction)}.
    @SuppressWarnings("unchecked")
    public <T extends Collector> T createOrGet(MetricDef<T> metricDef) {
        return (T) metrics.computeIfAbsent(metricDef.getMetricName(), s -> metricDef.getProducer().apply(metricDef.getMetricName(), registry));
    }
}
