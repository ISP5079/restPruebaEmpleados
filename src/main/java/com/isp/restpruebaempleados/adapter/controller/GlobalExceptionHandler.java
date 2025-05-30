package com.isp.restpruebaempleados.adapter.controller;

import com.isp.restpruebaempleados.adapter.dto.GlobalErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones del tipo {@code EntityNotFoundException}.
     *
     * @param ex la excepción {@code EntityNotFoundException} lanzada cuando no se encuentra una entidad
     * @return un {@code ResponseEntity} que contiene un {@code GlobalErrorResponse}
     *         con estado HTTP 404 (No Encontrado) y el mensaje de la excepción
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<GlobalErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new GlobalErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    /**
     * Maneja excepciones del tipo {@code MethodArgumentNotValidException} lanzadas durante la validación de argumentos de métodos.
     * Extrae los errores de validación y construye una respuesta de error personalizada con detalles sobre los campos inválidos.
     *
     * @param ex la excepción {@code MethodArgumentNotValidException} que contiene los errores de validación
     * @return un {@code ResponseEntity} que contiene un {@code GlobalErrorResponse}
     *         con estado HTTP 400 (Bad Request) y detalles sobre los errores de validación
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalErrorResponse> handleValidationError(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GlobalErrorResponse(HttpStatus.BAD_REQUEST, "Validation failed", errors));
    }

    /**
     * Maneja excepciones generales que no son tratadas explícitamente por otros métodos.
     * Registra el mensaje de la excepción y genera una respuesta de error genérica.
     *
     * @param ex la excepción que ocurrió
     * @return un {@code ResponseEntity} que contiene un {@code GlobalErrorResponse}
     *         con estado HTTP 500 (Error Interno del Servidor) y un mensaje de error genérico
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalErrorResponse> handleGeneralError(Exception ex) {
        log.error("Unhandled exception: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GlobalErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"));
    }
}
