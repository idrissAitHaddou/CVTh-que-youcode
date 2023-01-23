package com.example.cvtheque.learner;

import com.example.cvtheque.promotion.PromotionEntity;
import com.example.cvtheque.promotion.PromotionRepository;
import com.example.cvtheque.users.UserDto;
import com.example.cvtheque.users.UserEntity;
import com.example.cvtheque.users.UserRepository;
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
    @Autowired
    private UserDto userDto;
    @Autowired
    private UserRepository userRepository;

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

    public ResponseEntity<UserEntity> addUserService(UserDto user, String promoName){
        PromotionEntity promotion = promotionRepository.findByName(promoName);
        UserEntity userEntity = userDto.UserDtoToEntity(user);
        userEntity.getPromotions().add(promotion);
        UserEntity newUser =userRepository.save(userEntity);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    public void addLearnerService(UserDto user, String promoName){
        PromotionEntity promotion = promotionRepository.findByName(promoName);
        LearnerEntity userEntity = learnerDto.LearnerDtoToEntity(user);
        userEntity.getPromotions().add(promotion);
        learnerRepository.save(userEntity);
    }

}
