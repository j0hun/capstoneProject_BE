package com.example.demo.controller;

import com.example.demo.dto.SubFoodDto;
import com.example.demo.entity.SubFood;
import com.example.demo.service.FoodService;
import com.example.demo.service.SubFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubFoodController {

    private final SubFoodService subFoodService;
    private final FoodService foodService;

    @GetMapping("/api/subfoods/{foodId}")
    public ResponseEntity<List<SubFoodDto>> getSubFood(@PathVariable Long foodId) {
        List<SubFoodDto> subFoodDtoList = subFoodService.getSubFoods(foodId);
        return new ResponseEntity<>(subFoodDtoList,HttpStatus.OK);
    }

    @PostMapping("/api/subfoods/{foodId}")
    public ResponseEntity<SubFood> postSubFood(@PathVariable Long foodId, @RequestBody SubFoodDto subFoodDto) {
        SubFood subFood = subFoodService.postSubFood(subFoodDto,foodId);
        return new ResponseEntity<>(subFood, HttpStatus.CREATED);
    }

    @PutMapping("/api/subFoods/{foodId}/{subFoodId}")
    public ResponseEntity<Void> updateSubFood(@PathVariable Long foodId, @PathVariable Long subFoodId, @RequestBody SubFoodDto subFoodDto) {
        subFoodService.updateSubFood(subFoodDto,foodId,subFoodId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/subFoods/{subFoodId}")
    public ResponseEntity<Void> deleteSubFood(@PathVariable Long subFoodId) {
        subFoodService.deleteSubFood(subFoodId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
