package com.currency.exchange.exception;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalExceptionHandler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle Exception class.
     *
     * @param e - Exception Obj
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(final Exception e) {
        return new ResponseEntity<>(e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle NullPointerException class.
     *
     * @param e - Exception Obj
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(
            final NullPointerException e) {
        return new ResponseEntity<>(
                "Null Pointer Exception occurred: " + e.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle IllegalArgumentException class.
     *
     * @param e - Exception Obj
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(
            final IllegalArgumentException e) {
        return new ResponseEntity<>("Invalid argument: " + e.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle BadRequestException class.
     *
     * @param e - Exception Obj
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(
            final BadRequestException e) {
        return new ResponseEntity<>("Invalid argument: " + e.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

}
