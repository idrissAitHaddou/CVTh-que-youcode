package com.example.cvtheque.learner;

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
    public ResponseEntity<String> addLearner(@RequestBody LearnerDto learner, @RequestParam String promoName) {
        return learnerService.addLearnerService(learner, promoName);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<LearnerDto>> getLearners() {
        return learnerService.getAllLearnersService();
    }

}
