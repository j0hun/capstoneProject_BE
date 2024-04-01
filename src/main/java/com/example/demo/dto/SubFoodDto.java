package com.example.demo.dto;

import com.example.demo.entity.SubFood;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubFoodDto {

    private Long id;
    private String name;
    private Long foodId;

    public static SubFoodDto convertDTO(SubFood subFood){
        return new SubFoodDto(
                subFood.getId(),
                subFood.getName(),
                subFood.getFood().getId()
        );
    }
}
