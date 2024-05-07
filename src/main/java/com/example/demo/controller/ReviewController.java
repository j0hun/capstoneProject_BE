package com.example.demo.controller;

import com.example.demo.dto.ReviewRequestDTO;
import com.example.demo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{restaurantId}")
    public ResponseEntity<Void> postReview(@PathVariable Long restaurantId, @RequestBody ReviewRequestDTO reviewRequestDTO){
        reviewService.addReview(reviewRequestDTO,restaurantId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
