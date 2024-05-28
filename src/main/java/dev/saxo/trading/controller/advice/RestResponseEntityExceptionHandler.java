package dev.saxo.trading.controller.advice;

import dev.saxo.trading.errors.AccountNotFoundException;
import dev.saxo.trading.errors.DailyRiskException;
import dev.saxo.trading.errors.SymbolNotFoundException;
import dev.saxo.trading.errors.TotalRiskException;
import dev.saxo.trading.errors.web.SaxoResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { AccountNotFoundException.class, SymbolNotFoundException.class })
    protected ResponseEntity<Object> notFound(RuntimeException ex, WebRequest request) {
        SaxoResponse saxoResponse = SaxoResponse.builder().code(404).message(ex.getMessage()).build();
        return handleExceptionInternal(ex, saxoResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = { DailyRiskException.class, TotalRiskException.class })
    protected ResponseEntity<Object> conflict(RuntimeException ex, WebRequest request) {
        SaxoResponse saxoResponse = SaxoResponse.builder().code(409).message(ex.getMessage()).build();
        return handleExceptionInternal(ex, saxoResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
