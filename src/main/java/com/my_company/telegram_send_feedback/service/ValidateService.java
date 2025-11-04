package com.my_company.telegram_send_feedback.service;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidateService {
    public boolean validate(String regex, String validatable) {
        return Pattern.compile(regex)
                .matcher(validatable)
                .matches();
    }
}
