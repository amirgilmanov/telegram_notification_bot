package com.my_company.telegram_send_feedback.mapper;

import com.my_company.telegram_send_feedback.domain.dto.FeedBackRequestDto;
import com.my_company.telegram_send_feedback.domain.entity.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    @Mapping(target = "subject", source = "subjectRequest")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    Feedback toFeedback(FeedBackRequestDto dto);
}
