package com.my_company.telegram_send_feedback.domain.dto;

import com.my_company.telegram_send_feedback.domain.enums.SubjectRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedBackRequestDto {
    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @NotBlank(message = "Номер телефона не может быть пустым")
    @Pattern(
            regexp = "(\\+7|8)?[\\s-(]*\\d{3}[\\s)-]*\\d{3}[\\s-]*\\d{2}[\\s-]*\\d{2}",
            message = "Неверный формат номера телефона"
    )
    private String phone;

    @NotBlank(message = "Электронная почта не может быть пустой")
    @Pattern(
            regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "Неверный формат email"
    )
    private String email;

    private SubjectRequest subjectRequest;

    private String message;
}
