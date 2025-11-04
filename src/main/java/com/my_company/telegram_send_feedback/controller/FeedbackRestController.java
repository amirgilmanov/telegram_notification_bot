package com.my_company.telegram_send_feedback.controller;

import com.my_company.telegram_send_feedback.domain.dto.FeedBackRequestDto;
import com.my_company.telegram_send_feedback.service.FeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FeedbackRestController {
    private final FeedBackService feedBackService;

    @PostMapping("/send-request")
    public ResponseEntity<Void> sendFeedback(@RequestBody FeedBackRequestDto dto) {
        feedBackService.create(dto);
        return ResponseEntity.accepted().build();
    }
}
