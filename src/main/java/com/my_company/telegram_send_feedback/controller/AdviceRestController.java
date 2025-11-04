package com.my_company.telegram_send_feedback.controller;

import com.my_company.telegram_send_feedback.exception.InvalidEmailFormatException;
import com.my_company.telegram_send_feedback.exception.InvalidPhoneNumberFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceRestController {
    @ExceptionHandler(InvalidEmailFormatException.class)
    public ResponseEntity<String> handleInvalidEmailException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidPhoneNumberFormatException.class)
    public ResponseEntity<String> handleInvalidPhoneNumberFormatException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
