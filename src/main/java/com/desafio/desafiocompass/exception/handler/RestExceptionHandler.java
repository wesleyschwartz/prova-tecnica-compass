package com.desafio.desafiocompass.exception.handler;

import com.desafio.desafiocompass.exception.ErrorDetails;
import com.desafio.desafiocompass.exception.ResourceNotFoundDetails;
import com.desafio.desafiocompass.exception.ResourceNotFoundException;
import com.desafio.desafiocompass.exception.ValidationErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException) {
        ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.Builder
                .newBuilder()
                .status_code(HttpStatus.NOT_FOUND.value())
                .message(rfnException.getMessage())
                .build();
        return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException manvExc, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = manvExc.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fildsMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
        ValidationErrorDetails rnfDetails = ValidationErrorDetails.Builder
                .newBuilder()
                .status_code(HttpStatus.BAD_REQUEST.value())
                .message(manvExc.getMessage())
                .field(fields)
                .fieldMessage(fildsMessages)
                .build();
        return new ResponseEntity<>(rnfDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String msg2 = "Ocorreu um erro. Detalhes: " + ex.getMessage();
        ErrorDetails erros = ErrorDetails.Builder
                .newBuilder()
                .status_code(status.value())
                .message(msg2)
                .build();
        return new ResponseEntity<>(erros, headers, status);
    }
}

