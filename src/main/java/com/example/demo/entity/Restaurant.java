package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private double latitude;

    private double longitude;

    @OneToMany(mappedBy = "restaurant")
    @Builder.Default
    private List<Food> foodList = new ArrayList<>();

    public Restaurant(Long id,String name, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void updateRestaurant(Restaurant restaurant){
        this.name = restaurant.getName();
        this.latitude = restaurant.getLatitude();
        this.longitude = restaurant.getLongitude();
    }
}
