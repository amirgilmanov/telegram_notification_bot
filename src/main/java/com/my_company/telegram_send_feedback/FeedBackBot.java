package com.my_company.telegram_send_feedback;

import com.my_company.telegram_send_feedback.config.TelegramBotConfig;
import com.my_company.telegram_send_feedback.domain.entity.UserChat;
import com.my_company.telegram_send_feedback.repository.UserChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
@Slf4j
public class FeedBackBot extends TelegramLongPollingBot {
    private final TelegramBotConfig telegramBotConfig;
    private final UserChatRepository chatRepository;

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = getChatId(update);
            String name = getFirstName(update);
            UserChat userChat = UserChat.builder().chatId(chatId).build();
            chatRepository.save(userChat);

            String messageText = update.getMessage().getText();

            switch (messageText) {
                case "/start" -> greeting(chatId, name);
                case "/id" -> {
                    sendMessage(chatId, "Ваш id с ботом - %s".formatted(chatId));
                }
                default -> {
                    sendMessage(chatId, "Дефолтное сообщение");
                }
            }
        }
    }

    @Override
    public String getBotUsername() {

        return telegramBotConfig.getUsername();
    }

    private void greeting(Long chatId, String name) {
        String greeting = String.format("Добро пожаловать %s. Это бот для уведомлений обратной связи", name);
        sendMessage(chatId, greeting);
    }

    @Override
    public String getBotToken() {
        return telegramBotConfig.getToken();
    }

    public void sendMessage(Long chatId, String message) {
        log.info("Отправка сообщения");
        SendMessage sendMessage = new SendMessage(chatId.toString(), message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public String getFirstName(Update update) {
        return update.getMessage().getChat().getFirstName();
    }

    public Long getChatId(Update update) {
        return update.getMessage().getChatId();
    }
}
