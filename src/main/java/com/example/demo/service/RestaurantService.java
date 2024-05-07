package com.example.demo.service;

import com.example.demo.dto.RestaurantResponseDTO;
import com.example.demo.dto.RestaurantRequestDTO;
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
    public List<RestaurantResponseDTO> getRestaurants(){
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        List<RestaurantResponseDTO> restaurantDTOList = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            restaurantDTOList.add(RestaurantResponseDTO.toDTO(restaurant));
        }
        return restaurantDTOList;
    }

    @Transactional(readOnly = true)
    public RestaurantResponseDTO getRestaurant(Long restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(EntityNotFoundException::new);
        return RestaurantResponseDTO.toDTO(restaurant);
    }

    public Long postRestaurant(RestaurantRequestDTO restaurantDTO) {
        Restaurant restaurant = restaurantDTO.toEntity();
        return restaurant.getId();
    }

    public void updateRestaurant(RestaurantRequestDTO restaurantDTO, Long restaurantId){
        Restaurant foundRestaurant = restaurantRepository.findById(restaurantId).orElseThrow(EntityNotFoundException::new);
        Restaurant restaurant = restaurantDTO.toEntity();
        foundRestaurant.updateRestaurant(restaurant);
    }

    public void deleteRestaurant(Long restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(EntityNotFoundException::new);
        restaurantRepository.delete(restaurant);
    }

}
