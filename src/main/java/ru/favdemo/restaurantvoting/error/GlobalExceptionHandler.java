package ru.favdemo.restaurantvoting.error;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail body = ex.updateAndGetBody(this.messageSource, LocaleContextHolder.getLocale());
        Map<String, String> invalidParams = new LinkedHashMap<>();
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            invalidParams.put(error.getObjectName(), getErrorMessage(error));
        }
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            invalidParams.put(error.getField(), getErrorMessage(error));
        }
        body.setProperty("invalid_params", invalidParams);
        body.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        return handleExceptionInternal(ex, body, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(AppException.class)
    public ProblemDetail appException(AppException ex, WebRequest request) {
        log.error("AppException: {}", ex.getMessage());
        return createProblemDetail(ex, ex.getStatusCode(), request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ProblemDetail entityNotFoundException(WebRequest request, EntityNotFoundException ex) {
        log.error("EntityNotFoundException: {}", ex.getMessage());
        return createProblemDetail(ex, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(UpdateVoteException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ProblemDetail updateVote(WebRequest request, EntityNotFoundException ex) {
        log.error("UpdateVoteException: {}", ex.getMessage());
        return createProblemDetail(ex, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(IllegalRequestDataException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ProblemDetail illegalRequestData(WebRequest request, EntityNotFoundException ex) {
        log.error("IllegalRequestDataException: {}", ex.getMessage());
        return createProblemDetail(ex, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail notFound(WebRequest request, EntityNotFoundException ex) {
        log.error("NotFoundException: {}", ex.getMessage());
        return createProblemDetail(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(DataConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail dataConflict(WebRequest request, EntityNotFoundException ex) {
        log.error("DataConflictException: {}", ex.getMessage());
        return createProblemDetail(ex, HttpStatus.CONFLICT, request);
    }

    private ProblemDetail createProblemDetail(Exception ex, HttpStatusCode statusCode, WebRequest request) {
        return createProblemDetail(ex, statusCode, ex.getMessage(), request);
    }

    private ProblemDetail createProblemDetail(Exception ex, HttpStatusCode statusCode, @NonNull String msg, WebRequest request) {
        return createProblemDetail(ex, statusCode, msg, null, null, request);
    }

    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(
                error.getCode(), error.getArguments(), error.getDefaultMessage(), LocaleContextHolder.getLocale());
    }
}
