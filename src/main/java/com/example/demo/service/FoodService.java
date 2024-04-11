package com.example.demo.service;

import com.example.demo.constant.Allergy;
import com.example.demo.dto.FoodDto;
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
    public List<FoodDto> getAllByPriceRangeAndAllergies(Integer minPrice, Integer maxPrice,
                                                        Integer minCalories, Integer maxCalories,
                                                        List<Allergy> allergies){
        List<Food> foodList =  foodRepository.findAllByPriceRangeAndByCaloriesRangeAndAllergiesNotContained(minPrice, maxPrice, minCalories, maxCalories,allergies);
        List<FoodDto> foodDtoList = new ArrayList<>();
        for (Food food : foodList) {
            FoodDto foodDto = FoodDto.toDto(food);
            foodDtoList.add(foodDto);
        }
        return foodDtoList;
    }

    @Transactional(readOnly = true)
    public FoodDto getFood(Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(EntityNotFoundException::new);
        FoodDto foodDto = FoodDto.toDto(food);
        return foodDto;
    }

    @Transactional(readOnly = true)
    public List<FoodDto> getFoods() {
        List<Food> foodList = foodRepository.findAll();
        List<FoodDto> foodDtoList = new ArrayList<>();
        for (Food food : foodList) {
            FoodDto foodDto = FoodDto.toDto(food);
            foodDtoList.add(foodDto);
        }
        return foodDtoList;
    }

    public Food postFood(FoodDto foodDto) {
        Restaurant restaurant = restaurantRepository.findById(foodDto.getRestaurantId()).orElseThrow(EntityNotFoundException::new);
        Food food = new Food(foodDto.getId(),foodDto.getName(),foodDto.getPrice(),foodDto.getCalorie(),foodDto.getAllergyList());
        food.setRestaurant(restaurant);
        return foodRepository.save(food);
    }

    public void updateFood(FoodDto foodDto,Long foodId) {
        Food food = new Food(foodDto.getId(),foodDto.getName(),foodDto.getPrice(),foodDto.getCalorie(),foodDto.getAllergyList());
        Food foundFood = foodRepository.findById(foodId).orElseThrow(EntityNotFoundException::new);
        foundFood.updateFood(food);
    }

    public void deleteFood(Long foodId){
        foodRepository.deleteById(foodId);
    }

}
