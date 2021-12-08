package com.lukas8219.pollbe.exception;

import com.lukas8219.pollbe.data.dto.ErrorDTO;
import com.lukas8219.pollbe.data.dto.FieldErrorDTO;
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
import java.util.Objects;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdviceHandler {

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
    public ResponseEntity<ErrorDTO> handlePollException(CustomException exception){
        return ResponseEntity
                .status(exception.status())
                .body(
                        ErrorDTO.builder()
                                .status(exception.status().value())
                                .error(exception.getMessage()) // TODO add translations after
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handlePollException(Exception exception){
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
