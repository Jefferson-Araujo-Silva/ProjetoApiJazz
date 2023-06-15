package com.jazztech.api.client.execeptionhandler;

import com.jazztech.api.client.exception.AddressNotFoundException;
import com.jazztech.api.client.exception.CPFAlreadyExistException;
import com.jazztech.api.client.exception.ClientNotFoundException;
import jakarta.validation.ConstraintViolationException;
import java.net.URI;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClientExceptionHandler {
    public static final String TIMESTAMP = "timestamp";

    @ExceptionHandler(ClientNotFoundException.class)
    public ProblemDetail ClientNotFoundException(ClientNotFoundException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail(exception.getMessage());
        problemDetail.setType(URI.create("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status/400"));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ProblemDetail AddressNotFoundException(AddressNotFoundException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail(exception.getMessage());
        problemDetail.setType(URI.create("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status/400"));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(CPFAlreadyExistException.class)
    public ProblemDetail CPFAlreadyExistException(CPFAlreadyExistException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail(exception.getMessage());
        problemDetail.setType(URI.create("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status/400"));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail ConstraintViolationException(ConstraintViolationException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail(exception.getMessage());
        problemDetail.setType(URI.create("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status/404"));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        return problemDetail;
    }
}
