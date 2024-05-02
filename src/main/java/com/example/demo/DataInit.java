package com.example.demo;

import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.RestaurantRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @PostConstruct
    public void init(){
    }

}
