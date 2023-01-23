package com.example.cvtheque.document;

import com.example.cvtheque.cme.CmeEntity;
import com.example.cvtheque.learner.LearnerEntity;
import com.example.cvtheque.users.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "CommentDocs")
@Table(name = "commentdocs")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDocsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "id_document")
    private int idDocument;
    private String comment;
    private String created_at;
    @OneToMany(fetch = FetchType.EAGER)
    private List<CommentLikesEntity> commentLikes = new ArrayList<>();
    @OneToOne
    private LearnerEntity learner;
    @OneToOne
    private CmeEntity cme;
    @OneToOne
    private UserEntity user;
}
