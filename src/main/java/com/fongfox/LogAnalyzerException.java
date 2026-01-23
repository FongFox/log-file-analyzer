package com.fongfox;

public class LogAnalyzerException extends RuntimeException {

    /**
     * Constructor with a specific error message.
     * @param message The error message.
     */
    public LogAnalyzerException(String message) {
        super(message);
    }

    /**
     * Constructor that wraps another exception.
     * This is useful for chaining exceptions and preserving the original stack trace.
     * @param message The error message.
     * @param cause The original exception that caused this one.
     */
    public LogAnalyzerException(String message, Throwable cause) {
        super(message, cause);
    }
}
