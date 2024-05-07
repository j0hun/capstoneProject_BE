package com.example.demo.dto;

import com.example.demo.constant.Allergy;
import com.example.demo.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FoodResponseDTO {

    private Long id;
    private String name;
    private Integer price;
    private Integer calorie;
    private List<Allergy> allergyList;
    private Long restaurantId;

    public static FoodResponseDTO toDTO(Food food){
        return new FoodResponseDTO(food.getId(),food.getName(), food.getPrice(),food.getCalorie(),food.getAllergyList(),food.getRestaurant().getId());
    }
}
