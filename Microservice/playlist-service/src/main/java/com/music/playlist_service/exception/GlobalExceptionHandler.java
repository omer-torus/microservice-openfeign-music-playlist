package com.music.playlist_service.exception;

import com.music.playlist_service.dto.ErrorResponseDTO;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * 🌐 Global Exception Handler (@ControllerAdvice)
 * Catches exceptions thrown across the application and
 * returns meaningful JSON responses to the user instead of raw 500 errors.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Triggered when Feign cannot connect to a service (service down, timeout).
     * Returns HTTP 503 Service Unavailable.
     */
    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public ResponseEntity<ErrorResponseDTO> handleServiceUnavailable(FeignException.ServiceUnavailable ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "The target microservice is currently unavailable. Please try again later.",
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

    /**
     * Triggered when Feign receives a 404 (user or resource not found).
     * Returns HTTP 404 Not Found.
     */
    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(FeignException.NotFound ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                "Requested resource not found. Please check the ID.",
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * For general RuntimeExceptions (e.g., user has no favorite genres).
     * Returns HTTP 400 Bad Request.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(RuntimeException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * For unexpected errors not caught by any other handler.
     * Returns HTTP 500 Internal Server Error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred: " + ex.getMessage(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
