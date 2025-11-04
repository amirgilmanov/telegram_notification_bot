package com.my_company.telegram_send_feedback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.my_company.telegram_send_feedback.domain.enums.RegexPattern.RUSSIAN_PHONE_NUMBER_PATTERN;

@Service
@RequiredArgsConstructor
public class PhoneNumberValidateService implements Validatable, Normalize {
    private final ValidateService validateService;

    @Override
    public String normalize(String value) {
        //Оставляем только цифры
        String digits = value.replaceAll("\\D", "");

        if (digits.startsWith("8")) {
            digits = "7" + value.substring(1);
        } else if (!digits.startsWith("7")) {
            digits = "7" + value;
        }
        if (digits.length() == 11) {
            value = "+" + digits.substring(0, 1) + " (" +
                    digits.substring(1, 4) + ") " +
                    digits.substring(4, 7) + "-" +
                    digits.substring(7, 9) + "-" +
                    digits.substring(9, 11);
        }
        return value;
    }

    @Override
    public boolean validate(String value) {
        return validateService.validate(RUSSIAN_PHONE_NUMBER_PATTERN.getRegex(), value);
    }
}
