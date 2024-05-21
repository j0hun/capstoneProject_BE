package com.example.demo.controller;

import com.example.demo.dto.RestaurantResponseDTO;
import com.example.demo.dto.RestaurantRequestDTO;
import com.example.demo.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDTO>> getRestaurants(){
        List<RestaurantResponseDTO> restaurants = restaurantService.getRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurant(@PathVariable Long id){
        RestaurantResponseDTO restaurant = restaurantService.getRestaurant(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<RestaurantResponseDTO>> getRestaurantByCategory(@RequestParam String category,
                                                                               @RequestParam String location) {
        List<RestaurantResponseDTO> restaurants = restaurantService.getRestaurantByFilter(category,location);
        return new ResponseEntity<>(restaurants,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> postRestaurant(@RequestBody RestaurantRequestDTO restaurantDTO){
        restaurantService.postRestaurant(restaurantDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchRestaurant(@PathVariable Long id, @RequestBody RestaurantRequestDTO restaurantDTO) {
        restaurantService.updateRestaurant(restaurantDTO,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id){
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
