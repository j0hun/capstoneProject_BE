package com.example.demo.service;

import com.example.demo.constant.Allergy;
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
                                                                Integer minCalories, Integer maxCalories,
                                                                List<Allergy> allergies){
        List<Food> foodList =  foodRepository.findAllByPriceRangeAndByCaloriesRangeAndAllergiesNotContained(minPrice, maxPrice, minCalories, maxCalories,allergies);
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

    public Long postFood(FoodResponseDTO foodResponseDTO) {
        Restaurant restaurant = restaurantRepository.findById(foodResponseDTO.getRestaurantId()).orElseThrow(EntityNotFoundException::new);
        Food food = new Food(foodResponseDTO.getName(), foodResponseDTO.getPrice(), foodResponseDTO.getCalorie(), foodResponseDTO.getAllergyList());
        food.setRestaurant(restaurant);
        return food.getId();
    }

    public void updateFood(FoodResponseDTO foodResponseDTO, Long foodId) {
        Food food = new Food(foodResponseDTO.getName(), foodResponseDTO.getPrice(), foodResponseDTO.getCalorie(), foodResponseDTO.getAllergyList());
        Food foundFood = foodRepository.findById(foodId).orElseThrow(EntityNotFoundException::new);
        foundFood.updateFood(food);
    }

    public void deleteFood(Long foodId){
        foodRepository.deleteById(foodId);
    }

}
