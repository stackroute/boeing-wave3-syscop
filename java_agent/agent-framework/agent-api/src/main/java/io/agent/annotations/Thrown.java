package io.agent.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A parameter annotated with @Thrown can be used in a Hook's @After method to capture an Exception thrown in an instrumented method.
 *
 * Example: In order to instrument the following method:
 *
 * <pre>
 *     int div(int a, int b) {
 *         return a / b;
 *     }
 * </pre>
 *
 * A Hook could use an @After method like this:
 *
 * <pre>
 *    {@literal @}After(method = "div")
 *     void after(int a, int b, @Returned int result, @Thrown Throwable exception) {...}
 * </pre>
 *
 * In case everything goes well, the {@code result} will be the return value of {@code div()}, and {@code excption} will be {@code null}.
 * If {@code b} is {@code 0}, the {@code result} will be {@code 0}, and {@code exception} will be an {@link ArithmeticException} (division by zero).
 * <p/>
 * The parameter annotated with @Thrown is optional, if the hook does not use the exception, the parameter can be omitted.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Thrown {}
