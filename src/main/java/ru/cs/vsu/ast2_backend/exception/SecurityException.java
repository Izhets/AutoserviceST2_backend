package ru.cs.vsu.ast2_backend.exception;

public class SecurityException extends RuntimeException {
    public SecurityException() {
    }

    public SecurityException(String message) {
        super(message);
    }

    public SecurityException(String message, Object... args) {
        super(String.format(message, args));
    }
}
