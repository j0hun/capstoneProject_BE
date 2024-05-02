package com.example.demo.service;

import com.example.demo.constant.Allergy;
import com.example.demo.dto.FoodResponseDto;
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
    public List<FoodResponseDto> getAllByPriceRangeAndAllergies(Integer minPrice, Integer maxPrice,
                                                                Integer minCalories, Integer maxCalories,
                                                                List<Allergy> allergies){
        List<Food> foodList =  foodRepository.findAllByPriceRangeAndByCaloriesRangeAndAllergiesNotContained(minPrice, maxPrice, minCalories, maxCalories,allergies);
        List<FoodResponseDto> foodResponseDtoList = new ArrayList<>();
        for (Food food : foodList) {
            FoodResponseDto foodResponseDto = FoodResponseDto.toDto(food);
            foodResponseDtoList.add(foodResponseDto);
        }
        return foodResponseDtoList;
    }

    @Transactional(readOnly = true)
    public FoodResponseDto getFood(Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(EntityNotFoundException::new);
        FoodResponseDto foodResponseDto = FoodResponseDto.toDto(food);
        return foodResponseDto;
    }

    @Transactional(readOnly = true)
    public List<FoodResponseDto> getFoods() {
        List<Food> foodList = foodRepository.findAll();
        List<FoodResponseDto> foodResponseDtoList = new ArrayList<>();
        for (Food food : foodList) {
            FoodResponseDto foodResponseDto = FoodResponseDto.toDto(food);
            foodResponseDtoList.add(foodResponseDto);
        }
        return foodResponseDtoList;
    }

    public Food postFood(FoodResponseDto foodResponseDto) {
        Restaurant restaurant = restaurantRepository.findByName(foodResponseDto.getRestaurantName()).orElseThrow(EntityNotFoundException::new);
        Food food = new Food(foodResponseDto.getName(), foodResponseDto.getPrice(), foodResponseDto.getCalorie(), foodResponseDto.getAllergyList());
        food.setRestaurant(restaurant);
        return foodRepository.save(food);
    }

    public void updateFood(FoodResponseDto foodResponseDto, Long foodId) {
        Food food = new Food(foodResponseDto.getName(), foodResponseDto.getPrice(), foodResponseDto.getCalorie(), foodResponseDto.getAllergyList());
        Food foundFood = foodRepository.findById(foodId).orElseThrow(EntityNotFoundException::new);
        foundFood.updateFood(food);
    }

    public void deleteFood(Long foodId){
        foodRepository.deleteById(foodId);
    }

}
