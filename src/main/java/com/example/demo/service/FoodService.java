package com.example.demo.service;

import com.example.demo.dto.FoodRequestDTO;
import com.example.demo.dto.FoodResponseDTO;
import com.example.demo.entity.Food;
import com.example.demo.entity.Restaurant;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional(readOnly = true)
    public List<FoodResponseDTO> getAllByPriceRangeAndAllergies(Integer minPrice, Integer maxPrice,
                                                                Integer minCalories, Integer maxCalories){
        List<Food> foodList =  foodRepository.findAllByPriceRangeAndByCaloriesRangeAndAllergiesNotContained(minPrice, maxPrice, minCalories, maxCalories);
        List<FoodResponseDTO> foodResponseDTOList = new ArrayList<>();
        for (Food food : foodList) {
            FoodResponseDTO foodResponseDTO = FoodResponseDTO.toDTO(food);
            foodResponseDTOList.add(foodResponseDTO);
        }
        return foodResponseDTOList;
    }

    @Transactional(readOnly = true)
    public FoodResponseDTO getFood(Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(EntityNotFoundException::new);
        FoodResponseDTO foodResponseDTO = FoodResponseDTO.toDTO(food);
        return foodResponseDTO;
    }

    @Transactional(readOnly = true)
    public List<FoodResponseDTO> getFoods() {
        List<Food> foodList = foodRepository.findAll();
        List<FoodResponseDTO> foodResponseDTOList = new ArrayList<>();
        for (Food food : foodList) {
            FoodResponseDTO foodResponseDTO = FoodResponseDTO.toDTO(food);
            foodResponseDTOList.add(foodResponseDTO);
        }
        return foodResponseDTOList;
    }

    public Long postFood(FoodRequestDTO foodRequestDTO) {
        Restaurant restaurant = restaurantRepository.findById(foodRequestDTO.getRestaurantId()).orElseThrow(EntityNotFoundException::new);
        Food food = new Food(foodRequestDTO.getName(), foodRequestDTO.getPrice(), foodRequestDTO.getCalorie());
        food.setRestaurant(restaurant);
        foodRepository.save(food);
        return food.getId();
    }

    public void updateFood(FoodRequestDTO foodRequestDTO, Long foodId) {
        Food food = new Food(foodRequestDTO.getName(), foodRequestDTO.getPrice(), foodRequestDTO.getCalorie());
        Food foundFood = foodRepository.findById(foodId).orElseThrow(EntityNotFoundException::new);
        foundFood.updateFood(food);
    }

    public void deleteFood(Long foodId){
        foodRepository.deleteById(foodId);
    }

}
