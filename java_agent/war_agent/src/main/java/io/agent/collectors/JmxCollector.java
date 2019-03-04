package io.agent.collectors;

import com.sun.management.OperatingSystemMXBean;
import io.prometheus.client.Collector;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// TODO -- the following is an experiment supporting collectors directly (in addition to hooks)
// TODO -- This class is not loaded by default, see commented-out lines in io.agent.internal.agent.premain()
// See JmxCollector in jmx_exporter
public class JmxCollector extends Collector implements Collector.Describable {

    @Override
    public List<MetricFamilySamples> collect() {
        List<MetricFamilySamples> result = new ArrayList<>();
        result.add(collectOperatingSystemMetrics());
        return Collections.unmodifiableList(result);
    }

    @Override
    public List<MetricFamilySamples> describe() {
        return new ArrayList<>();
    }

    private MetricFamilySamples collectOperatingSystemMetrics() {
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        MetricFamilySamples.Sample cpuLoad = new MetricFamilySamples.Sample("process_cpu_load", new ArrayList<>(), new ArrayList<>(), operatingSystemMXBean.getProcessCpuLoad());
        return new MetricFamilySamples(cpuLoad.name, Type.GAUGE, "recent cpu usage for the whole system", Arrays.asList(cpuLoad));
    }
}
