package io.agent.agent;

import java.lang.instrument.Instrumentation;

/**
 * We want as little dependencies as possible on the system class loader,
 * so the actual agent is loaded in its own class loader and this agent delegates to it.
 */
public class agent {

    public static void premain(String agentArgs, Instrumentation inst) throws Exception {
        ClassLoader agentClassLoader = ClassLoaderCache.getInstance().currentClassLoader();
        Class<?> agentClass = agentClassLoader.loadClass("io.agent.internal.agent");
        agentClass.getMethod("premain", String.class, Instrumentation.class).invoke(null, agentArgs, inst);
    }

    public static void agentmain(String agentArgs, Instrumentation inst) throws Exception {
        premain(agentArgs, inst);
    }
}
