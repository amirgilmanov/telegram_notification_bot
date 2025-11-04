package com.my_company.telegram_send_feedback.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_api")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column()
    private String name;
    @Column()
    private String phone;
    @Column()
    private String email;

    @OneToMany(mappedBy = "user", fetch = LAZY)
    private List<Feedback> feedbacks;
}
