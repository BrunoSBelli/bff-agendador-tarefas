package com.raposo.bffagendadortarefas.infrastructure.exceptions;

public class BusinesseException extends RuntimeException {
    public BusinesseException(String message) {
        super(message);
    }

    public BusinesseException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
