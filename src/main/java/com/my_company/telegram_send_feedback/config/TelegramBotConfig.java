package com.my_company.telegram_send_feedback.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Slf4j
public class TelegramBotConfig {
    @Value("${bot.telegram_bot_token}")
    private String token;
    @Value("${bot.telegram_bot_username}")
    private String username;
}
