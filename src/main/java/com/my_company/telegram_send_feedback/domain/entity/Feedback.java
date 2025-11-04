package com.my_company.telegram_send_feedback.domain.entity;

import com.my_company.telegram_send_feedback.domain.enums.SubjectRequest;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "feedback")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Feedback {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private SubjectRequest subject;

    @Column
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}

