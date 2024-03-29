package com.example.demo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    @Transactional(readOnly = true)
    public List<Food> getFoods(){
        return foodRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Food getFood(Long id){
        return foodRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
