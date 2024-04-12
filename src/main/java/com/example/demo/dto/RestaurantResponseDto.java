package com.example.demo.dto;

import com.example.demo.entity.Food;
import com.example.demo.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class RestaurantResponseDto {

    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    private List<FoodDto> foodDtoList;

    public static RestaurantResponseDto toDto(Restaurant restaurant){
        List<FoodDto> foodDtoList = new ArrayList<>();
        List<Food> foodList = restaurant.getFoodList();
        for (Food food : foodList) {
            FoodDto foodDto = new FoodDto(food.getId(),food.getName(),food.getPrice(),food.getCalorie(),food.getAllergyList(),food.getRestaurant().getId());
            foodDtoList.add(foodDto);
        }
        return new RestaurantResponseDto(restaurant.getId(),restaurant.getName(),restaurant.getLatitude(),restaurant.getLongitude(),foodDtoList);
    }


}
