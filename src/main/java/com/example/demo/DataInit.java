package com.example.demo;

import com.example.demo.constant.Allergy;
import com.example.demo.entity.Food;
import com.example.demo.entity.SubFood;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.SubFoodRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final FoodRepository foodRepository;
    private final SubFoodRepository subFoodRepository;

    @PostConstruct
    public void init(){
        Food food1 = foodRepository.save(new Food("부대찌개", "학생식당", 5000, 1240, Arrays.asList(Allergy.Beef, Allergy.Buckwheat)));
        SubFood subFood1 = subFoodRepository.save(new SubFood("감자고코레,케찹", food1));
    }

}
