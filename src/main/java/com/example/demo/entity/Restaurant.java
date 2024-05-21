package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String location;

    private String category;

    private double latitude;

    private double longitude;

    @OneToMany(mappedBy = "restaurant")
    private List<Food> foodList = new ArrayList<>();

    @Builder
    public Restaurant(String name, String category,String location, double latitude, double longitude) {
        this.name = name;
        this.category = category;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void updateRestaurant(Restaurant restaurant){
        this.name = restaurant.getName();
        this.category = restaurant.getCategory();
        this.location = restaurant.getLocation();
        this.latitude = restaurant.getLatitude();
        this.longitude = restaurant.getLongitude();
    }
}
