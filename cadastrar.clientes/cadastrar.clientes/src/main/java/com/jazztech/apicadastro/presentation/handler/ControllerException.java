package com.jazztech.apicadastro.presentation.handler;

import feign.FeignException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerException {
    private static final Logger logger = LoggerFactory.getLogger(ControllerException.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorHandler> handle(FeignException exception) {
        logger.info(String.valueOf(exception));
        ErrorHandler errorHandler = new ErrorHandler("Cep inválido", HttpStatus.NOT_FOUND.value(),
                "Revise as informações do seu CEP", "/client", LocalDateTime.now());
        return new ResponseEntity<>(errorHandler, HttpStatus.NOT_FOUND);
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorHandler> handle(ClientNotFoundException exception) {
        logger.info(String.valueOf(exception));
        ErrorHandler errorHandler = new ErrorHandler("Client not found", HttpStatus.NOT_FOUND.value(),
                "Cliente não encontrado!", String.format(("/clients/id")), LocalDateTime.now());
        return new ResponseEntity<>(errorHandler, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(ClientNotCreatedException.class)
    public ResponseEntity<ErrorHandler> handle(ClientNotCreatedException exception) {
        logger.info(String.valueOf(exception));
        ErrorHandler errorHandler = new ErrorHandler("Client not created", HttpStatus.NOT_IMPLEMENTED.value(),
                "Cliente não foi cadastrado!", "/clients", LocalDateTime.now());
        return new ResponseEntity<>(errorHandler, HttpStatus.NOT_IMPLEMENTED);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorHandler> handle(ConstraintViolationException exception) {
        logger.error(String.valueOf(exception));
        ErrorHandler errorHandler = new ErrorHandler("Invalid values", HttpStatus.NOT_FOUND.value(),
                "Revise as informações de seus dados, não pode estar em branco", "/clients", LocalDateTime.now());
        return new ResponseEntity<>(errorHandler, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<ErrorHandler> handle(DateTimeException exception) {
        logger.error(String.valueOf(exception) + "Formato de data inválido");
        ErrorHandler errorHandler = new ErrorHandler("Invalid Date", HttpStatus.NOT_FOUND.value(),
                "Revise a sua data de nascimento, deve estar em formato YYYY/MM/DD", "/clients/", LocalDateTime.now());
        return new ResponseEntity<>(errorHandler, HttpStatus.NOT_FOUND);
    }

}