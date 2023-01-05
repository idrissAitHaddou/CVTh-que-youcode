package com.example.cvtheque.cme;

import com.example.cvtheque.document.CommentDocsEntity;
import com.example.cvtheque.document.CommentLikesEntity;
import com.example.cvtheque.users.UserEntity;
import com.example.cvtheque.promotion.PromotionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Cme")
@Table(name = "cmes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CmeEntity extends UserEntity {
    @ManyToMany(mappedBy = "cmes",fetch = FetchType.EAGER)
    private List<PromotionEntity> promotions = new ArrayList<>();
    @OneToOne
    private CommentLikesEntity commentLikes;
    @OneToMany
    private List<CommentDocsEntity> commentDocs;
}
