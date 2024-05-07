package com.example.demo.dto;

import com.example.demo.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewResponseDTO {

    private Long id;
    private String content;

    public static ReviewResponseDTO toDTO(Review review){
        return new ReviewResponseDTO(review.getId(), review.getContent());
    }

}
