package com.example.demo.dto;

import com.example.demo.constant.Allergy;
import com.example.demo.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FoodDto {

    private Long id;
    private String name;
    private Integer price;
    private Integer calorie;
    private List<Allergy> allergyList;
    private Long restaurantId;

    public static FoodDto toDto(Food food){
        return new FoodDto(food.getId(),food.getName(), food.getPrice(),food.getCalorie(),food.getAllergyList(),food.getRestaurant().getId());
    }
}
