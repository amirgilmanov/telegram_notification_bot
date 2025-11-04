package com.my_company.telegram_send_feedback.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler
    public String handleException(Exception e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "Произошла ошибка - %s".formatted(e.getMessage()));
        return "redirect:/feedback";
    }
}
