package io.agent.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A parameter annotated with @Returned can be used in a Hook's @After method to capture the return value of the instrumented method.
 *
 * Example: In order to instrument the following method:
 * The parameter annotated with @Returned is optional, if the hook does not use the return value, the parameter can be omitted.
 * If the instrumented method terminates exceptionally, the type's default value is assigned to the parameter,
 * i.e. {@code 0} for numeric types and {@code null} for reference types.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Returned {}
