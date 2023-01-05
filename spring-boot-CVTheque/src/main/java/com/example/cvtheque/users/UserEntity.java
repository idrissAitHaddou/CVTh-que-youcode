package com.example.cvtheque.users;

import jakarta.persistence.*;
import lombok.Data;

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
}
