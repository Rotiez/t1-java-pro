package edu.t1.limitservice.exception;

public class OverLimitException extends RuntimeException {
    public OverLimitException(String message) { super(message); }
}
