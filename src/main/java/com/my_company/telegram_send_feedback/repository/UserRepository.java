package com.my_company.telegram_send_feedback.repository;

import com.my_company.telegram_send_feedback.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
