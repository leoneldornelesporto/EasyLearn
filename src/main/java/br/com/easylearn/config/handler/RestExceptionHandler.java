package br.com.easylearn.config.handler;

import br.com.easylearn.config.exception.ResourceBadRequestException;
import br.com.easylearn.config.exception.ResourceNotFoundDetails;
import br.com.easylearn.config.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        ResourceNotFoundDetails rfnDetails = ResourceNotFoundDetails.ResourceNotFoundDetailsBuilder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource Not Found")
                .detail(resourceNotFoundException.getMessage())
                .developerMessage(resourceNotFoundException.getClass().getName())
                .build();

        return new ResponseEntity<>(rfnDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<?> handleResourceBadRequestException(ResourceBadRequestException resourceBadRequestException){
        ResourceNotFoundDetails rfnDetails = ResourceNotFoundDetails.ResourceNotFoundDetailsBuilder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Resource Bad Request")
                .detail(resourceBadRequestException.getMessage())
                .developerMessage(resourceBadRequestException.getClass().getName())
                .build();

        return new ResponseEntity<>(rfnDetails,HttpStatus.BAD_REQUEST);
    }
}
