package com.example.demo.dto;

import com.example.demo.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestaurantRequestDTO {

    private String name;
    private double latitude;
    private double longitude;

    public Restaurant toEntity() {
        return new Restaurant(this.name,this.latitude,this.longitude);
    }

}
