package com.example.cvtheque.users;

import com.example.cvtheque.learner.LearnerDto;
import com.example.cvtheque.learner.LearnerEntity;
import com.example.cvtheque.promotion.PromotionDto;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String image;
    private String role;
    private List<PromotionDto> promotions;

    public static UserDto UserEntityToDto(UserEntity user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .image(user.getImage())
                .role(user.getRole())
                .build();
    }

}
