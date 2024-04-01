package com.example.demo.dto;

import com.example.demo.constant.Allergy;
import com.example.demo.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class FoodDto {

    private Long id;
    private String name;
    private String category;
    private Integer price;
    private Integer calorie;
    private List<Allergy> allergyList;
    private List<SubFoodDto> subFoodList;

    public static FoodDto convertDTO(Food food){
        List<SubFoodDto> subFoodDtos = new ArrayList<>();
        food.getSubFoodList().forEach(x-> subFoodDtos.add(SubFoodDto.convertDTO(x)));
        return new FoodDto(
                food.getId(),
                food.getName(),
                food.getCategory(),
                food.getPrice(),
                food.getCalorie(),
                food.getAllergyList(),
                subFoodDtos
        );
    }

}
