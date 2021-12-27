package com.lukas8219.pollbe.exception;

import com.lukas8219.pollbe.data.dto.ErrorDTO;
import com.lukas8219.pollbe.data.dto.FieldErrorDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@RequiredArgsConstructor
public class ControllerAdviceHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleBean(MethodArgumentNotValidException ex) {

        var errors = new ArrayList<>();

        if (!CollectionUtils.isEmpty(ex.getBindingResult().getAllErrors())) {
            ex.getBindingResult().getFieldErrors().forEach(error -> errors.add(FieldErrorDTO.builder()
                    .field(error.getField())
                    .message(error.getDefaultMessage())
                    .rejectedValue(Objects.requireNonNullElse(error.getRejectedValue(), "").toString())
                    .build()));
        }

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(
                        ErrorDTO.builder()
                                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                                .fieldsError(errors)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDTO> handlePollException(CustomException exception) {
        log.error(exception.getLocalizedMessage(), exception);
        return ResponseEntity
                .status(exception.status())
                .body(
                        ErrorDTO.builder()
                                .status(exception.status().value())
                                .error(translateError(exception)) // TODO add translations after
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    private String translateError(CustomException exception) {
        if (exception.getMessage() == null) {
            return null;
        }
        return messageSource.getMessage(exception.getMessage(), null, Locale.getDefault());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handlePollException(Exception exception) {
        log.error(exception.getLocalizedMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ErrorDTO.builder()
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .error("Ocorreu um erro inesperado. Nossa equipe já foi avisada")
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }
}
