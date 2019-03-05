package io.agent.internal;

public class HookException extends RuntimeException {

    public HookException(String message) {
        super(message);
    }

    public HookException(String message, Throwable cause) {
        super(message, cause);
    }
}
