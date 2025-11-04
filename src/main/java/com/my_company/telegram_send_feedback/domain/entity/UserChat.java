package com.my_company.telegram_send_feedback.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_chat")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserChat {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long chatId;
}
