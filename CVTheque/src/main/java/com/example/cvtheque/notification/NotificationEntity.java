package com.example.cvtheque.notification;

import com.example.cvtheque.document.DocumentEntity;
import com.example.cvtheque.learner.LearnerEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Notification")
@Table(name = "notifications")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String description;
    private String link;
    @Column(name = "created_at")
    private String createdAt;
    @OneToOne
    private LearnerEntity learner;
    @OneToOne
    private DocumentEntity document;

}
