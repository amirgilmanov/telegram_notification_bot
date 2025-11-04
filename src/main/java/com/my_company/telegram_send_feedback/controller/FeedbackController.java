package com.my_company.telegram_send_feedback.controller;

import com.my_company.telegram_send_feedback.domain.dto.FeedBackRequestDto;
import com.my_company.telegram_send_feedback.domain.enums.SubjectRequest;
import com.my_company.telegram_send_feedback.service.FeedBackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedBackService feedBackService;

    @GetMapping
    public String showForm(@ModelAttribute("feedbackDto") FeedBackRequestDto feedbackDto, Model model) {
        if (!model.containsAttribute("feedbackDto")) {
            model.addAttribute("feedbackDto", new FeedBackRequestDto());
        }
        model.addAttribute("subjects", SubjectRequest.values());
        return "feedback-form";
    }

    @PostMapping("/send")
    public String handleFeedback(
            @Valid @ModelAttribute("feedbackDto") FeedBackRequestDto feedbackDto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("subjects", SubjectRequest.values());
            return "feedback-form";
        }
        try {
            feedBackService.create(feedbackDto);
            redirectAttributes.addFlashAttribute("successMessage", "Ваше обращение успешно отправлено!");
            return "redirect:/feedback/success";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Произошла ошибка при отправке обращения %s".formatted(e.getMessage()));
            model.addAttribute("subjects", SubjectRequest.values());
            return "feedback-form";
        }
    }

    @GetMapping("/success")
    public String showSuccess(Model model) {
        if (!model.containsAttribute("successMessage")) {
            model.addAttribute("successMessage", "Ваше обращение успешно отправлено!");
        }
        return "success";
    }
}