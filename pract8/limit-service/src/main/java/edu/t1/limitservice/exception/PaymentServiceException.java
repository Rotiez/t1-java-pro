package edu.t1.limitservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class PaymentServiceException extends RuntimeException{
    private HttpStatusCode statusCode;
    public PaymentServiceException(String message, HttpStatusCode statusCode) { super(message); }
}
