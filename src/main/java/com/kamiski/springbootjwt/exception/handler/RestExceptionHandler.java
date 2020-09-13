package com.kamiski.springbootjwt.exception.handler;

import com.kamiski.springbootjwt.exception.*;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SignatureTokenException.class)
    public ResponseEntity<SignatureTokenException> handleInvalidToken(SignatureTokenException e){
        return new ResponseEntity(
                ExceptionDetail.builder()
                        .title("Forbidden")
                        .status(HttpStatus.FORBIDDEN.value())
                        .detail(e.getClass().getName())
                        .developerMessage(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ExpiredTokenException> handleExpiredToken(ExpiredTokenException e){
        return new ResponseEntity(
                ExceptionDetail.builder()
                        .title("Forbidden")
                        .status(HttpStatus.FORBIDDEN.value())
                        .detail(e.getClass().getName())
                        .developerMessage(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<AuthException> handleUsernameExists(AuthException e){
        return new ResponseEntity(
                ExceptionDetail.builder()
                        .title("Bad Request")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .detail(e.getClass().getName())
                        .developerMessage(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UserNameException.class)
    public ResponseEntity<UserNameException> handleUsernameExists(UserNameException e){
        return new ResponseEntity(
                ExceptionDetail.builder()
                        .title("Bad Request")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .detail(e.getClass().getName())
                        .developerMessage(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundException> handleResourceNotFound(ResourceNotFoundException e){
        return new ResponseEntity(
                ExceptionDetail.builder()
                        .title("Resource not found")
                        .status(HttpStatus.NOT_FOUND.value())
                        .detail(e.getClass().getName())
                        .developerMessage(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.NOT_FOUND
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .title("Bad Request")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .field(fields)
                        .fieldMessage(fieldsMessage)
                        .timestamp(LocalDateTime.now())
                        .developerMessage(ex.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST
        );

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDetail exceptionDetails = ExceptionDetail.builder()
                .title(ex.getCause().getMessage())
                .status(status.value())
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity(exceptionDetails, headers, status);
    }

}
