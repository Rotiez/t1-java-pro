package com.rotiez.paymentservice.exception.handler;

import com.rotiez.paymentservice.dto.ExceptionDto;
import com.rotiez.paymentservice.exception.InsufficientBalanceException;
import com.rotiez.paymentservice.exception.NotFoundException;
import com.rotiez.paymentservice.exception.ProductServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductServiceException.class)
    protected ResponseEntity<ExceptionDto> handleProductServiceException(
            ProductServiceException ex, WebRequest request) {
        return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), ex.getStatusCode());
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    protected ResponseEntity<ExceptionDto> handleInsufficientBalanceException(
            InsufficientBalanceException ex, WebRequest request) {
        return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ExceptionDto> handleNotFoundException(
            NotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionDto> handleException(Exception ex, WebRequest request) {
        log.error("Unexpected error", ex);
        log.error("Cause: ", ex.getCause());

        String message = (log.isDebugEnabled()) ? ex.getMessage() : "Внутренняя ошибка сервиса";
        return new ResponseEntity<>(new ExceptionDto(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}