package com.rotiez.productservice.exception.handler;

import com.rotiez.productservice.exception.ExceptionDto;
import com.rotiez.productservice.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ExceptionDto> handleNotFoundException(Exception ex, WebRequest request) {
        log.error("Resource not found", ex);
        log.error("Cause: ", ex.getCause());

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