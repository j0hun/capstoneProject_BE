package com.example.demo.controller;

import com.example.demo.dto.ReviewRequestDTO;
import com.example.demo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

//    @PostMapping("/{restaurantId}")
//    public ResponseEntity<Void> postReview(@PathVariable Long restaurantId, @RequestBody ReviewRequestDTO reviewRequestDTO, Principal principal){
//        String email = principal.getName();
//        reviewService.addReview(reviewRequestDTO,restaurantId,email);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @PostMapping("/{restaurantId}")
    public ResponseEntity<Void> postReview(@PathVariable Long restaurantId, @RequestBody ReviewRequestDTO reviewRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            String email = authentication.getPrincipal().toString();
            reviewService.addReview(reviewRequestDTO, restaurantId, email);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            // 인증되지 않은 사용자에 대한 처리 로직
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
