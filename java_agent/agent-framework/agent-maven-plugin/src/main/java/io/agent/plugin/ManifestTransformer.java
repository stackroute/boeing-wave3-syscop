
package io.agent.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.descriptor.PluginDescriptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.Manifest;

class ManifestTransformer {

    private static final Attributes.Name PREMAIN_CLASS = new Attributes.Name("Premain-Class");
    private static final Attributes.Name CREATED_BY = new Attributes.Name("Created-By");

    private final String pluginArtifactId;
    private final String pluginVersion;

    ManifestTransformer(PluginDescriptor pluginDescriptor) {
        pluginArtifactId = pluginDescriptor.getArtifactId();
        pluginVersion = pluginDescriptor.getVersion();
    }

    boolean canTransform(JarEntry jarEntry) {
        return "META-INF/MANIFEST.MF".equals(jarEntry.getName());
    }

    InputStream transform(InputStream inputStream) throws MojoExecutionException {
        try (InputStream in = inputStream) { // No need for new variable in Java 9.
            Manifest manifest = new Manifest(in);
            Attributes attributes = manifest.getMainAttributes();
            if (!attributes.containsKey(PREMAIN_CLASS)) {
                throw new MojoExecutionException(PREMAIN_CLASS + " not found in MANIFEST.MF. This is a bug in agent-maven-plugin.");
            }
            attributes.put(CREATED_BY, pluginArtifactId + ":" + pluginVersion);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            manifest.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new MojoExecutionException("Failed to transform MANIFEST.MF: " + e.getMessage(), e);
        }
    }

    JarEntry transform(JarEntry entry) {
        return new JarEntry(entry.getName());
    }
}
