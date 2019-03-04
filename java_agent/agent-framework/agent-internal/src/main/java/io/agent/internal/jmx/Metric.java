

package io.agent.internal.jmx;

import io.prometheus.client.Collector;

import java.util.HashMap;
import java.util.Map;

public class Metric implements MetricMBean {

    private final Collector metric;

    Metric(Collector metric) {
        this.metric = metric;
    }

    /**
     * @see MetricMBean#getValues()
     */
    @Override
    public Map<Map<String, String>, Double> getValues() {
        Map<Map<String, String>, Double> result = new HashMap<>();
        for (Collector.MetricFamilySamples samples : metric.collect()) {
            for (Collector.MetricFamilySamples.Sample sample : samples.samples) {
                Map<String, String> labels = new HashMap<>();
                for (int i = 0; i < sample.labelNames.size(); i++) {
                    labels.put(sample.labelNames.get(i), sample.labelValues.get(i));
                }
                result.put(labels, sample.value);
            }
        }
        return result;
    }
}
