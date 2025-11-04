package com.my_company.telegram_send_feedback.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class EmailValidateServiceTest {
    @Mock
    ValidateService validateService;
    @InjectMocks
    EmailValidateService emailValidateService;

    @Test
    void validateEmail() {
        String email = "amir.com";
        boolean isValid = emailValidateService.validate(email);
        Assertions.assertFalse(isValid);
    }

    @Test
    void validateInvalidEmail() {
        String email = "amir@.com";
        boolean isValid = emailValidateService.validate(email);
        Assertions.assertFalse(isValid);
    }

    @Test
    @Disabled
    void successValidateEmail() {
        String email = "amir123@gmail.com";
        boolean isValid = emailValidateService.validate(email);
        Assertions.assertTrue(isValid);
    }

}