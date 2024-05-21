package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodRequestDTO {

    private String name;
    private Integer price;
    private Integer calorie;
    private Long restaurantId;

}
