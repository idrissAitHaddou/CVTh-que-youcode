package com.example.cvtheque.promotion;

import com.example.cvtheque.cme.CmeEntity;
import com.example.cvtheque.former.FormerEntity;
import com.example.cvtheque.learner.LearnerEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Promotion")
@Table(name = "promotions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PromotionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String year;
    private String level;
    private String image;
    private String startDate;
    private String endtDate;
    private String descritpion;
    @ManyToMany
    private List<FormerEntity> formers;
    @ManyToMany
    private List<CmeEntity> cmes;
    @ManyToMany(mappedBy = "promotions",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<LearnerEntity> learners = new ArrayList<>();
}
