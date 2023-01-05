package com.example.cvtheque.document;

import com.example.cvtheque.cme.CmeEntity;
import com.example.cvtheque.learner.LearnerEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "CommentLikes")
@Table(name = "commentlikes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentLikesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String type;
    @OneToOne(fetch = FetchType.EAGER)
    private LearnerEntity learner;
    @OneToOne(fetch = FetchType.EAGER)
    private CmeEntity cme;
    @OneToOne
    private CommentDocsEntity commentDocs;
}
