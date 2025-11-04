package com.my_company.telegram_send_feedback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.my_company.telegram_send_feedback.domain.enums.RegexPattern.EMAIL_REGEX_PATTERN;

@Service
@RequiredArgsConstructor
public class EmailValidateService implements Validatable {
    private final ValidateService validateService;

    @Override
    public boolean validate(String email) {
        return validateService.validate(EMAIL_REGEX_PATTERN.getRegex(), email);
    }
}
