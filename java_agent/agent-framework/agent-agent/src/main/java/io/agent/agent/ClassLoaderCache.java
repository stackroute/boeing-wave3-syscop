package io.agent.agent;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassLoaderCache stores the class loaders used for loading the agent modules,
 * i.e. agent-hooks, agent-annotations, agent-internals, and their dependencies.
 * <p/>
 * For the Hooks (like ServletHook or JdbcHook) there is one class loader per deployment,
 * because hook classes may reference classes from the deployment,
 * e.g. as parameters to the before() and after() methods.
 * All other modules and their dependencies are loaded through a shared class loader.
 * <p/>
 */
public class ClassLoaderCache {

    private static ClassLoaderCache instance;

    // TODO: The cache does not free class loaders when applications are undeployed. Maybe use WeakHashMap?
    private final Map<ClassLoader, PerDeploymentClassLoader> cache = new HashMap<>();
    private final URLClassLoader sharedClassLoader; // shared across multiple deployments
    private final List<Path> perDeploymentJars; // one class loader for each deployment for these JARs

    private ClassLoaderCache(JarFiles jarFiles) {
        sharedClassLoader = new URLClassLoader(pathsToURLs(jarFiles.getSharedJars()));
        perDeploymentJars = jarFiles.getPerDeploymentJars();
    }

    public static synchronized ClassLoaderCache getInstance() {
        if (instance == null) {
            instance = new ClassLoaderCache(JarFiles.extract());
        }
        return instance;
    }

    public List<Path> getPerDeploymentJars() {
        return perDeploymentJars;
    }

    public synchronized ClassLoader currentClassLoader() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (! cache.containsKey(contextClassLoader)) {
            cache.put(contextClassLoader, new PerDeploymentClassLoader(pathsToURLs(perDeploymentJars), sharedClassLoader, contextClassLoader));
        }
        return cache.get(contextClassLoader);
    }

    private static URL[] pathsToURLs(List<Path> paths) {
        try {
            URL[] result = new URL[paths.size()];
            for (int i=0; i<paths.size(); i++) {
                result[i] = paths.get(i).toUri().toURL();
            }
            return result;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
