package com.my_company.telegram_send_feedback.mapper;

import com.my_company.telegram_send_feedback.domain.dto.FeedBackRequestDto;
import com.my_company.telegram_send_feedback.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.my_company.telegram_send_feedback.domain.enums.SubjectRequest.SALES;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserMapperTest {
    private final UserMapperImpl userMapper = new UserMapperImpl();

    @Test
    void successMapToUser() {
        //Подготовка данных
        FeedBackRequestDto dto = new FeedBackRequestDto("test", "+79999999999", "test@mail.com", SALES, "test");
        //Проверка
        User user = userMapper.toUser(dto);
        assertDoesNotThrow(() -> userMapper.toUser(dto));
        assertNotNull(user);
        assertEquals("test", user.getName());
        assertEquals("+79999999999", user.getPhone());
        assertEquals("test@mail.com", user.getEmail());
    }
}