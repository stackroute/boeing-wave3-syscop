

package io.agent.internal.jmx;

import java.util.Map;

public interface MetricMBean {

    /**
     * Get the values in a representation that can be used in MXBeans in JMX.
     * <p/>
     * The result is a map of labels -> value.
     * The labels themselves are represented as a key -> value map.
     */
    Map<Map<String, String>, Double> getValues();
}
