package com.example.demo.service;

import com.example.demo.dto.RestaurantResponseDto;
import com.example.demo.dto.RestaurantRequestDto;
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
    public List<RestaurantResponseDto> getRestaurants(){
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        List<RestaurantResponseDto> restaurantDtoList = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            restaurantDtoList.add(RestaurantResponseDto.toDto(restaurant));
        }
        return restaurantDtoList;
    }

    @Transactional(readOnly = true)
    public RestaurantResponseDto getRestaurant(Long restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(EntityNotFoundException::new);
        return RestaurantResponseDto.toDto(restaurant);
    }

    public Restaurant postRestaurant(RestaurantRequestDto restaurantDto) {
        Restaurant restaurant = restaurantDto.toEntity();
        return restaurantRepository.save(restaurant);
    }

    public void updateRestaurant(RestaurantRequestDto restaurantDto, Long restaurantId){
        Restaurant foundRestaurant = restaurantRepository.findById(restaurantId).orElseThrow(EntityNotFoundException::new);
        Restaurant restaurant = restaurantDto.toEntity();
        foundRestaurant.updateRestaurant(restaurant);
    }

    public void deleteRestaurant(Long restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(EntityNotFoundException::new);
        restaurantRepository.delete(restaurant);
    }

}
