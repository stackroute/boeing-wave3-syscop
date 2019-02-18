package io.agent.internal.jmx;

import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.SimpleCollector;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;

/**
 * This is like the regular {@link CollectorRegistry}, except that when you {@link #register(Collector)} a metric,
 * the metric will also be registered as an MBean in the JMX platform server.
 */
public class agentCollectorRegistry extends CollectorRegistry {

    @Override
    public void register(Collector metric) {
        super.register(metric);
        try {
            ManagementFactory.getPlatformMBeanServer().registerMBean(new Metric(metric), makeObjectName((SimpleCollector) metric));
        } catch (Exception e) {
            throw new RuntimeException("Failed to register  metric: " + e.getMessage(), e);
        }
    }

    private static ObjectName makeObjectName(SimpleCollector metric) throws MalformedObjectNameException {
        return makeObjectName(getFullName(metric));
    }

    private static ObjectName makeObjectName(String fullname) throws MalformedObjectNameException {
        return new ObjectName("io.agent:type=metrics,name=" + fullname);
    }

    private static String getFullName(SimpleCollector metric) {
        // Unfortunately, there is no public API to get the 'fullname' of a metric. We use reflection to get it anyway.
        try {
            Field field = SimpleCollector.class.getDeclaredField("fullname");
            field.setAccessible(true);
            return (String) field.get(metric);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException("Failed to access " + metric.getClass().getName() + ".fullname. " +
                    "This is probably because the internal implementation of the client library has changed. " +
                    "You should adapt the agent accordingly.", e);
        }
    }

    public void registerNoJmx(Collector collector) {
        super.register(collector);
    }
}
