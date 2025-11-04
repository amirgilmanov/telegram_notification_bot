package com.my_company.telegram_send_feedback.mapper;

import com.my_company.telegram_send_feedback.domain.dto.FeedBackRequestDto;
import com.my_company.telegram_send_feedback.domain.entity.Feedback;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.my_company.telegram_send_feedback.domain.enums.SubjectRequest.SALES;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class FeedbackMapperTest {
    private final FeedbackMapperImpl feedbackMapper = new FeedbackMapperImpl();

    @Test
    void successMapToFeedback() {
        //Подготовка данных
        FeedBackRequestDto dto = new FeedBackRequestDto("test", "+79999999999", "test@mail.com", SALES, "test");
        //Проверка
        Feedback feedback = feedbackMapper.toFeedback(dto);
        assertDoesNotThrow(() -> feedbackMapper.toFeedback(dto));
        assertNotNull(feedback);
        assertEquals(SALES, feedback.getSubject());
    }
}