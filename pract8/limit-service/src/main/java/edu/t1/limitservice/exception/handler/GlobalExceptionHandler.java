package edu.t1.limitservice.exception.handler;

import edu.t1.limitservice.dto.ExceptionDto;
import edu.t1.limitservice.exception.OverLimitException;
import edu.t1.limitservice.exception.PaymentServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PaymentServiceException.class)
    protected ResponseEntity<ExceptionDto> handlePaymentServiceException(PaymentServiceException ex) {
        return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), ex.getStatusCode());
    }

    @ExceptionHandler(OverLimitException.class)
    protected ResponseEntity<ExceptionDto> handleOverLimitException(OverLimitException ex) {
        return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionDto> handleException(Exception ex, WebRequest request) {
        log.error("Unexpected error", ex);
        log.error("Cause: ", ex.getCause());

        String message = (log.isDebugEnabled()) ? ex.getMessage() : "Внутренняя ошибка сервиса";
        return new ResponseEntity<>(new ExceptionDto(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
