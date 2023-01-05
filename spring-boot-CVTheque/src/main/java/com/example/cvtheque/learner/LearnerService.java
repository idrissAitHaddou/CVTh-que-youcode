package com.example.cvtheque.learner;

import com.example.cvtheque.promotion.PromotionEntity;
import com.example.cvtheque.promotion.PromotionRepository;
import com.example.cvtheque.users.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearnerService {
    @Autowired
    private LearnerRepository learnerRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private LearnerDto learnerDto;

    public LearnerDto findLearnerById(int id) {
        Long idUser = Long.valueOf(id);
        Optional<LearnerEntity> learner = learnerRepository.findById(idUser);
        return learnerDto.LearnerEntityToDto(learner.get());
    }

    public LearnerDto loadUserByEmail(String email) {
        LearnerDto learner = learnerDto.LearnerEntityToDto(learnerRepository.findByEmail(email));
        return learner;
    }

    public ResponseEntity<List<LearnerDto>> getAllLearnersService() {
        List<LearnerDto> learners = learnerDto.learnerDtoList(learnerRepository.findAll());
        return new ResponseEntity<>(learners, HttpStatus.OK);
    }

    public ResponseEntity<String> addLearnerService(LearnerDto learner, String promoName){
        PromotionEntity promotion = promotionRepository.findByName(promoName);
        LearnerEntity newLerner = learnerDto.LearnerDtoToEntity(learner);
        newLerner.getPromotions().add(promotion);
        learnerRepository.save(newLerner);
        return new ResponseEntity<>("learner created", HttpStatus.CREATED);
    }

}
