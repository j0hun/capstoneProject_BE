package com.example.demo.dto;

import com.example.demo.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FoodResponseDTO {

    private Long id;
    private String name;
    private Integer price;
    private Integer calorie;
    private Long restaurantId;

    public static FoodResponseDTO toDTO(Food food){
        return new FoodResponseDTO(food.getId(),food.getName(), food.getPrice(),food.getCalorie(),food.getRestaurant().getId());
    }
}
