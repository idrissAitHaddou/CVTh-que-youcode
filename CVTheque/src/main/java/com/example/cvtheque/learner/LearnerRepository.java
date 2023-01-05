package com.example.cvtheque.learner;

import com.example.cvtheque.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LearnerRepository extends JpaRepository<LearnerEntity, Long> {
    public LearnerEntity findByEmail(String email);
//    @Query( "select l from LearnerEntity l")
//    List<LearnerEntity> getAllLearners();
}
