package com.example.demo.controller;

import com.example.demo.constant.Allergy;
import com.example.demo.dto.FoodDto;
import com.example.demo.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/api/foodsByCondition")
    public ResponseEntity<List<FoodDto>> getFoodsByCondition(@RequestParam("minPrice") Integer minPrice,
                                                             @RequestParam("maxPrice") Integer maxPrice,
                                                             @RequestParam("minCalories") Integer minCalories,
                                                             @RequestParam("maxCalories") Integer maxCalories,
                                                             @RequestParam(value = "allergies", required = false) String[] allergiesArray) {
        List<Allergy> allergies = new ArrayList<>();
        if (allergiesArray != null) {
            allergies = Arrays.stream(allergiesArray)
                    .map(Allergy::valueOf)
                    .collect(Collectors.toList());
        }

        List<FoodDto> foods = foodService.getAllByPriceRangeAndAllergies(minPrice, maxPrice, minCalories, maxCalories, allergies);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }


    @GetMapping("/api/foods")
    public ResponseEntity<List<FoodDto>> getFoods(){
        List<FoodDto> foods = foodService.getFoods();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/api/foods/{id}")
    public ResponseEntity<FoodDto> getFood(@PathVariable Long id) {
        FoodDto food = foodService.getFood(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @PostMapping("/api/foods")
    public ResponseEntity<Void> postFood(@RequestBody FoodDto foodDto){
        foodService.postFood(foodDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/api/foods/{foodId}")
    public ResponseEntity<Void> putFood(@PathVariable Long foodId, @RequestBody FoodDto foodDto) {
        foodService.updateFood(foodDto,foodId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/foods/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
