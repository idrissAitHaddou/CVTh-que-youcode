package com.example.cvtheque.learner;

import com.example.cvtheque.document.CommentDocsEntity;
import com.example.cvtheque.document.CommentLikesEntity;
import com.example.cvtheque.document.DocumentEntity;
import com.example.cvtheque.notification.NotificationEntity;
import com.example.cvtheque.promotion.PromotionEntity;
import com.example.cvtheque.users.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "learners")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LearnerEntity extends UserEntity implements Serializable {
    @ManyToMany(targetEntity = PromotionEntity.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PromotionEntity> promotions = new ArrayList<>();
    @OneToMany
    private List<NotificationEntity> notifications;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DocumentEntity> documents = new ArrayList<>();
    @OneToOne
    private CommentLikesEntity commentLikes;
    @OneToMany
    private List<CommentDocsEntity> commentDocs;
}
