package io.agent.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The @Hook annotation indicates that the annotated class is a Hook.
 * The parameter {@link Hook#instruments()} defines which classes or interfaces are instrumented by that Hook.
 * The method annotations {@link Before} and {@link After} define which methods should be instrumented within that class or interface.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Hook {

    /**
     * List of classes or interfaces to be instrumented.
     */
    String[] instruments();

    /**
     * If true, nested calls are skipped.
     * If set to {@code false}, nested calls will also be instrumented.
     * For nested calls, the same Hook instance is re-used.
     * For outer calls, a new Hook instance is created.
     */
    boolean skipNestedCalls() default true;
}
