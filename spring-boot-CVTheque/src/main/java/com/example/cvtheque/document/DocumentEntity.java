package com.example.cvtheque.document;

import com.example.cvtheque.learner.LearnerEntity;
import com.example.cvtheque.notification.NotificationEntity;
import com.example.cvtheque.users.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Document")
@Table(name = "documents")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String description;
    private String src;
    private String type;
    private String category;
    @Column(name = "created_at")
    private String createdAt;
    private byte[] data;
    @Lob
    @Column(columnDefinition = "text")
    private String data_file;
    @ManyToOne
    private LearnerEntity learner;
    @ManyToOne
    private UserEntity user;
    @OneToMany
    private List<NotificationEntity> notification;

}
