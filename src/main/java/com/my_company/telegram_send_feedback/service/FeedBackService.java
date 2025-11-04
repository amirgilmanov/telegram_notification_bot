package com.my_company.telegram_send_feedback.service;

import com.my_company.telegram_send_feedback.FeedBackBot;
import com.my_company.telegram_send_feedback.domain.dto.FeedBackRequestDto;
import com.my_company.telegram_send_feedback.domain.entity.Feedback;
import com.my_company.telegram_send_feedback.domain.entity.User;
import com.my_company.telegram_send_feedback.exception.InvalidEmailFormatException;
import com.my_company.telegram_send_feedback.exception.InvalidPhoneNumberFormatException;
import com.my_company.telegram_send_feedback.mapper.FeedbackMapper;
import com.my_company.telegram_send_feedback.mapper.UserMapper;
import com.my_company.telegram_send_feedback.repository.FeedBackRepository;
import com.my_company.telegram_send_feedback.repository.UserChatRepository;
import com.my_company.telegram_send_feedback.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedBackService {
    private final FeedBackRepository feedBackRepository;
    private final FeedbackMapper feedbackMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final FeedBackBot feedBackBot;
    private final UserChatRepository userChatRepository;
    private final EmailValidateService emailValidateService;
    private final PhoneNumberValidateService phoneNumberValidateService;

    //text
    //📞 *Новый запрос на звонок!*
    //--------------------------
    //*Имя:* Иван Иванов
    //*Телефон:* `+7 (999) 123-45-67`
    //*Email:* ivanov@example.com
    //*Тема:* Техническая поддержка
    //*Сообщение:*
    //У меня не работает кнопка отправки формы на главной странице.
    @Transactional
    public void create(FeedBackRequestDto dto) {
        boolean isValidEmail = emailValidateService.validate(dto.getEmail());
        if (!isValidEmail) {
            log.warn("Электронная почта не соответствует требованиям");

            throw new InvalidEmailFormatException();
        }

        boolean isPhoneValid = phoneNumberValidateService.validate(dto.getPhone());
        if (!isPhoneValid) {
            log.warn("Phone is not valid");

            throw new InvalidPhoneNumberFormatException("Номер телефона не соответствует требованиям");
        }
        phoneNumberValidateService.normalize(dto.getPhone());
        User user = userMapper.toUser(dto);
        userRepository.save(user);
        Feedback feedback = feedbackMapper.toFeedback(dto);
        feedback.setUser(user);
        feedBackRepository.save(feedback);

        String m = """
                    📞 *Новый запрос на звонок!*
                    --------------------------
                    *Имя:* %s
                    *Телефон:* `%s`
                    *Email:* %s
                    *Тема:* %s
                    *Сообщение:*
                    %s
                """.formatted(dto.getName(), dto.getPhone(), dto.getEmail(), dto.getSubjectRequest().getValue(), dto.getMessage());
        userChatRepository.findAll().forEach(userChat -> feedBackBot.sendMessage(userChat.getChatId(), m));
    }
}
