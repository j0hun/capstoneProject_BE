package com.example.demo.controller;

import com.example.demo.constant.Allergy;
import com.example.demo.dto.FoodResponseDTO;
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
@RequestMapping("/api/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/foodsByCondition")
    public ResponseEntity<List<FoodResponseDTO>> getFoodsByCondition(@RequestParam(value = "minPrice",required = false) Integer minPrice,
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

        List<FoodResponseDTO> foods = foodService.getAllByPriceRangeAndAllergies(minPrice, maxPrice, minCalories, maxCalories, allergies);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<FoodResponseDTO>> getFoods(){
        List<FoodResponseDTO> foods = foodService.getFoods();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodResponseDTO> getFood(@PathVariable Long id) {
        FoodResponseDTO food = foodService.getFood(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> postFood(@RequestBody FoodResponseDTO foodResponseDTO){
        foodService.postFood(foodResponseDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{foodId}")
    public ResponseEntity<Void> putFood(@PathVariable Long foodId, @RequestBody FoodResponseDTO foodResponseDTO) {
        foodService.updateFood(foodResponseDTO,foodId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
