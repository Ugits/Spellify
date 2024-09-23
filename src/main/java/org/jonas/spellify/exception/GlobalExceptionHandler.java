package org.jonas.spellify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SpellAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handleSpellAlreadyExists(SpellAlreadyExists e) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    @ExceptionHandler(SpellApiException.class)
    public ResponseEntity<ErrorResponse> handleSpellApiException(SpellApiException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_GATEWAY.value(),
                e.getMessage(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(errorResponse);
    }

    @ExceptionHandler(SpellNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSpellNotFoundException(SpellNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage() + " --> Refer to documentation for input guidelines! <--",
                LocalDateTime.now().toString()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(SpellUpdateException.class)
    public ResponseEntity<ErrorResponse> handleSpellUpdateException(SpellUpdateException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred: " + e.getMessage(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(SpellValidationException.class)
    public ResponseEntity<ErrorResponse> handleSpellValidationException(SpellValidationException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage() + " --> Refer to documentation for input guidelines! <--",
                LocalDateTime.now().toString()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ErrorResponse> handleMissingPathVariableException(MissingPathVariableException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage() + " --> Refer to documentation for input guidelines! <--",
                LocalDateTime.now().toString()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
