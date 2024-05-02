package com.example.demo.controller;

import com.example.demo.constant.Allergy;
import com.example.demo.dto.FoodResponseDto;
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
    public ResponseEntity<List<FoodResponseDto>> getFoodsByCondition(@RequestParam(value = "minPrice",required = false) Integer minPrice,
                                                                     @RequestParam(value = "maxPrice",required = false) Integer maxPrice,
                                                                     @RequestParam(value = "minCalories",required = false) Integer minCalories,
                                                                     @RequestParam(value = "maxCalories",required = false) Integer maxCalories,
                                                                     @RequestParam(value = "allergies", required = false) String[] allergiesArray) {
        List<Allergy> allergies = new ArrayList<>();
        if (allergiesArray != null) {
            allergies = Arrays.stream(allergiesArray)
                    .map(Allergy::valueOf)
                    .collect(Collectors.toList());
        }

        List<FoodResponseDto> foods = foodService.getAllByPriceRangeAndAllergies(minPrice, maxPrice, minCalories, maxCalories, allergies);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }


    @GetMapping("/api/foods")
    public ResponseEntity<List<FoodResponseDto>> getFoods(){
        List<FoodResponseDto> foods = foodService.getFoods();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/api/foods/{id}")
    public ResponseEntity<FoodResponseDto> getFood(@PathVariable Long id) {
        FoodResponseDto food = foodService.getFood(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @PostMapping("/api/foods")
    public ResponseEntity<Void> postFood(@RequestBody FoodResponseDto foodResponseDto){
        foodService.postFood(foodResponseDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/api/foods/{foodId}")
    public ResponseEntity<Void> putFood(@PathVariable Long foodId, @RequestBody FoodResponseDto foodResponseDto) {
        foodService.updateFood(foodResponseDto,foodId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/foods/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
