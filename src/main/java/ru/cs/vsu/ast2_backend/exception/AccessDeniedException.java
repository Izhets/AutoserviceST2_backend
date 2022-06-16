package ru.cs.vsu.ast2_backend.exception;

public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Object... args) {
        super(String.format(message, args));
    }
}
