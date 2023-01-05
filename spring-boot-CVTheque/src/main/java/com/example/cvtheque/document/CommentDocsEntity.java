package com.example.cvtheque.document;

import com.example.cvtheque.cme.CmeEntity;
import com.example.cvtheque.learner.LearnerEntity;
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
    private int id_document;
    private String comment;
    @OneToMany(fetch = FetchType.EAGER)
    private List<CommentLikesEntity> commentLikes = new ArrayList<>();
    @OneToOne
    private LearnerEntity learner;
    @OneToOne
    private CmeEntity cme;
}
