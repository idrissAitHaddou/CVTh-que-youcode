package com.example.cvtheque.learner;

import com.example.cvtheque.document.DocumentEntity;
import com.example.cvtheque.promotion.PromotionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LearnerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String image;
    private List<PromotionEntity> promotions = new ArrayList<>();
    private List<DocumentEntity> documents = new ArrayList<>();

    public LearnerDto LearnerEntityToDto(LearnerEntity learner){
        return LearnerDto.builder()
                .id(learner.getId())
                .firstName(learner.getFirstName())
                .lastName(learner.getLastName())
                .email(learner.getEmail())
                .image(learner.getImage())
                .promotions(learner.getPromotions())
                .build();
    }

    public LearnerEntity LearnerDtoToEntity(LearnerDto learnerDto){
        LearnerEntity learnerEntity = new LearnerEntity();
        learnerEntity.setId(learnerDto.getId());
        learnerEntity.setFirstName(learnerDto.getFirstName());
        learnerEntity.setLastName(learnerDto.getLastName());
        learnerEntity.setEmail(learnerDto.getEmail());
        learnerEntity.setImage(learnerDto.getImage());
        learnerEntity.setPassword(learnerDto.getPassword());
        return learnerEntity;
    }

    public List<LearnerDto> learnerDtoList(List<LearnerEntity> leaners) {
        List<LearnerDto> learnerDtoList = new ArrayList<>();
        leaners.forEach(learner -> {
           LearnerDto learnerDto = LearnerDto.builder()
                    .id(learner.getId())
                    .firstName(learner.getFirstName())
                    .lastName(learner.getLastName())
                    .email(learner.getEmail())
                    .image(learner.getImage())
                    .promotions(learner.getPromotions())
                    .documents(learner.getDocuments())
                    .build();
            learnerDtoList.add(learnerDto);
        });
        return learnerDtoList;
    }
}
