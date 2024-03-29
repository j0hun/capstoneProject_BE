package com.example.demo;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final FoodRepository foodRepository;

    @PostConstruct
    public void init(){
        Food food1 = foodRepository.save(new Food("부대찌개"));
    }

}
