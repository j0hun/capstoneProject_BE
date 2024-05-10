package com.example.demo.service;

import com.example.demo.dto.ReviewRequestDTO;
import com.example.demo.entity.Member;
import com.example.demo.entity.Restaurant;
import com.example.demo.entity.Review;
import com.example.demo.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    public Long addReview(ReviewRequestDTO reviewRequestDTO, Long restaurantId,String email) {
        Review review = reviewRequestDTO.toEntity();
        Member member = memberRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(EntityNotFoundException::new);
        review.changeRestaurant(restaurant);
        review.changeMember(member);
        reviewRepository.save(review);
        return review.getId();
    }

}
