package com.my_company.telegram_send_feedback.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RegexPattern {
    /**
     * Допускаются числовые значения от 0 до 9.
     * <p>
     * Допускаются как заглавные, так и строчные буквы от a до z.
     * <p>
     * Разрешены подчеркивание «_», дефис «-» и точка «».
     * <p>
     * Точка не допускается в начале и конце локальной части.
     * <p>
     * Последовательные точки не допускаются.
     * <p>
     * Для локальной части допускается максимум 64 символа.
     */
    EMAIL_REGEX_PATTERN("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"),

    RUSSIAN_PHONE_NUMBER_PATTERN("(\\+7|8)?[\\s-(]*\\d{3}[\\s)-]*\\d{3}[\\s-]*\\d{2}[\\s-]*\\d{2}");
    public final String regex;
}
