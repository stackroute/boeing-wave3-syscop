package io.agent.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The method annotated with @Before is executed before entering the instrumented method.
 * The method annotated with @Before must have exactly the same parameters as the instrumented method.
 * The "method" parameter are the names of the instrumented methods.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Before {
    String[] method();
}
