package com.example.demo.service;

import com.example.demo.dto.ReviewRequestDTO;
import com.example.demo.entity.Restaurant;
import com.example.demo.entity.Review;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    public Long addReview(ReviewRequestDTO reviewRequestDTO, Long restaurantId) {
        Review review = reviewRequestDTO.toEntity();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(EntityNotFoundException::new);
        review.changeRestaurant(restaurant);
        reviewRepository.save(review);
        return review.getId();
    }

}
