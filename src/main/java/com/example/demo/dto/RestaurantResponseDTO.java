package com.example.demo.dto;

import com.example.demo.entity.Food;
import com.example.demo.entity.Restaurant;
import com.example.demo.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class RestaurantResponseDTO {

    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    private List<FoodResponseDTO> foodResponseDTOList;
    private List<ReviewResponseDTO> reviewResponseDTOList;

    public static RestaurantResponseDTO toDTO(Restaurant restaurant){
        List<FoodResponseDTO> foodResponseDTOList = new ArrayList<>();
        List<Food> foodList = restaurant.getFoodList();
        for (Food food : foodList) {
            FoodResponseDTO foodResponseDTO = FoodResponseDTO.toDTO(food);
            foodResponseDTOList.add(foodResponseDTO);
        }
        List<ReviewResponseDTO> reviewResponseDTOList = new ArrayList<>();
        List<Review> reviewList = restaurant.getReviewList();
        for (Review review : reviewList) {
            ReviewResponseDTO reviewResponseDTO = ReviewResponseDTO.toDTO(review);
            reviewResponseDTOList.add(reviewResponseDTO);

        }
        return new RestaurantResponseDTO(restaurant.getId(),restaurant.getName(),restaurant.getLatitude(),restaurant.getLongitude(), foodResponseDTOList,reviewResponseDTOList);
    }


}
