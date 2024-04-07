package com.example.demo.dto;

import com.example.demo.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestaurantDto {

    private String name;
    private double latitude;
    private double longitude;

    public static RestaurantDto toDto(Restaurant restaurant){
        return new RestaurantDto(restaurant.getName(),restaurant.getLatitude(),restaurant.getLongitude());
    }

    public Restaurant toEntity() {
        return new Restaurant(this.name,this.latitude,this.longitude);
    }

}