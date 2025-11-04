package com.my_company.telegram_send_feedback.repository;

import com.my_company.telegram_send_feedback.domain.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository extends JpaRepository<Feedback, Long> {
}
