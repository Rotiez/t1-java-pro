package com.rotiez.paymentservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class ProductServiceException extends RuntimeException {
    private HttpStatusCode statusCode;
    public ProductServiceException(String message, HttpStatusCode statusCode) { super(message); }
}
