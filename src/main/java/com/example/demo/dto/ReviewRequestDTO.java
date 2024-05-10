package com.example.demo.dto;

import com.example.demo.entity.Review;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDTO {

    private String content;

    public Review toEntity(){
        return new Review(content);
    }

}