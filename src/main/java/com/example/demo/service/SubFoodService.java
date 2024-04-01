package com.example.demo.service;

import com.example.demo.dto.SubFoodDto;
import com.example.demo.entity.Food;
import com.example.demo.entity.SubFood;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.SubFoodRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SubFoodService {

    private final SubFoodRepository subFoodRepository;
    private final FoodRepository foodRepository;

    @Transactional(readOnly = true)
    public List<SubFoodDto> getSubFoods(Long foodId){
        List<SubFood> subFoodList = subFoodRepository.findAllByFoodId(foodId);
        List<SubFoodDto> subFoodDtoList = new ArrayList<>();
        for (SubFood subFood : subFoodList) {
            SubFoodDto subFoodDto = SubFoodDto.convertDTO(subFood);
            subFoodDtoList.add(subFoodDto);
        }
        return subFoodDtoList;
    }

    public SubFood postSubFood(SubFoodDto subFoodDto,Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(EntityNotFoundException::new);
        SubFood subFood = new SubFood(subFoodDto.getName(),food);
        return subFoodRepository.save(subFood);
    }

    public void updateSubFood(SubFoodDto subFoodDto,Long foodId, Long subFoodId) {
        SubFood foundSubFood = subFoodRepository.findById(subFoodId).orElseThrow(EntityNotFoundException::new);
        Food food = foodRepository.findById(foodId).orElseThrow(EntityNotFoundException::new);
        SubFood subFood = new SubFood(subFoodDto.getName(),food);
        foundSubFood.updateSubFood(subFood);
    }

    public void deleteSubFood(Long subFoodId) {
        SubFood subFood = subFoodRepository.findById(subFoodId).orElseThrow(EntityNotFoundException::new);
        subFoodRepository.delete(subFood);
    }

}
