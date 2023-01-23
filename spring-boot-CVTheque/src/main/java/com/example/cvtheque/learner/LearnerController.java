package com.example.cvtheque.learner;

import com.example.cvtheque.users.UserDto;
import com.example.cvtheque.users.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/learner")
public class LearnerController {
    @Autowired
    private LearnerService learnerService;

    @PostMapping("/add")
    public ResponseEntity<UserEntity> addLearner(@RequestBody UserDto user, @RequestParam String promoName) {
        return learnerService.addUserService(user, promoName);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<LearnerDto>> getLearners() {
        return learnerService.getAllLearnersService();
    }

}
