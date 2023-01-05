package com.example.cvtheque.former;

import com.example.cvtheque.users.UserEntity;
import com.example.cvtheque.promotion.PromotionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Former")
@Table(name = "formers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FormerEntity extends UserEntity {
    @ManyToMany(mappedBy = "formers",fetch = FetchType.EAGER)
    private List<PromotionEntity> promotions = new ArrayList<>();
}
