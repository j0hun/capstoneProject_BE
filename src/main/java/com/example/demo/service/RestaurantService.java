package com.example.demo.service;

import com.example.demo.dto.RestaurantDto;
import com.example.demo.entity.Restaurant;
import com.example.demo.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional(readOnly = true)
    public List<RestaurantDto> getRestaurants(){
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        List<RestaurantDto> restaurantDtoList = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            restaurantDtoList.add(RestaurantDto.toDto(restaurant));
        }
        return restaurantDtoList;
    }

    @Transactional(readOnly = true)
    public RestaurantDto getRestaurant(Long restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(EntityNotFoundException::new);
        return RestaurantDto.toDto(restaurant);
    }

    public Restaurant postRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantDto.toEntity();
        return restaurantRepository.save(restaurant);
    }

    public void updateRestaurant(RestaurantDto restaurantDto, Long restaurantId){
        Restaurant foundRestaurant = restaurantRepository.findById(restaurantId).orElseThrow(EntityNotFoundException::new);
        Restaurant restaurant = restaurantDto.toEntity();
        foundRestaurant.updateRestaurant(restaurant);
    }

    public void deleteRestaurant(Long restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(EntityNotFoundException::new);
        restaurantRepository.delete(restaurant);
    }

}
