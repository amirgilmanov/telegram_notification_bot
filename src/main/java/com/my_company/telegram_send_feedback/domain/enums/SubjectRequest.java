package com.my_company.telegram_send_feedback.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SubjectRequest {
    GENERAL_QUESTION("Общий вопрос"),
    TECHNICAL_SUPPORT("Техническая поддержка"),
    SALES("Продажи");
    private final String value;
}
