package com.example.cvtheque.users;

import com.example.cvtheque.learner.LearnerDto;
import com.example.cvtheque.learner.LearnerEntity;
import com.example.cvtheque.promotion.PromotionDto;
import com.example.cvtheque.promotion.PromotionEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
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
    private List<PromotionEntity> promotions = new ArrayList<>();

    public UserDto UserEntityToDto(UserEntity user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .image(user.getImage())
                .role(user.getRole())
                .promotions(user.getPromotions())
                .build();
    }

    public UserEntity UserDtoToEntity(UserDto userDto){
        UserEntity user = new UserEntity();
                user.setId(userDto.getId());
                user.setFirstName(userDto.getFirstName());
                user.setLastName(userDto.getLastName());
                user.setEmail(userDto.getEmail());
                user.setImage(userDto.getImage());
                user.setRole(userDto.getRole());
                user.setPassword(userDto.getPassword());
                user.setPromotions(userDto.getPromotions());
                return user;
    }

}
