package com.example.cvtheque.users;

import com.example.cvtheque.document.CommentDocsEntity;
import com.example.cvtheque.document.DocumentEntity;
import com.example.cvtheque.promotion.PromotionEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "User")
@Table(name = "users")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String image;
    private String role;
    @ManyToMany(targetEntity = PromotionEntity.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PromotionEntity> promotions = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DocumentEntity> documents = new ArrayList<>();
}
