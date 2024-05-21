package com.example.demo.dto;

import com.example.demo.entity.Food;
import com.example.demo.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class RestaurantResponseDTO {

    private Long id;
    private String name;
    private String location;
    private double latitude;
    private double longitude;
    private List<FoodResponseDTO> foodResponseDTOList;

    public static RestaurantResponseDTO toDTO(Restaurant restaurant) {
        List<FoodResponseDTO> foodResponseDTOList = new ArrayList<>();
        List<Food> foodList = restaurant.getFoodList();
        for (Food food : foodList) {
            FoodResponseDTO foodResponseDTO = FoodResponseDTO.toDTO(food);
            foodResponseDTOList.add(foodResponseDTO);
        }
        return new RestaurantResponseDTO(restaurant.getId(), restaurant.getName(), restaurant.getLocation(), restaurant.getLatitude(), restaurant.getLongitude(), foodResponseDTOList);
    }
}
