package com.github.matheusferreiral.algafoodapi.api;

import com.github.matheusferreiral.algafoodapi.domain.exception.EntityNotFoundException;
import com.github.matheusferreiral.algafoodapi.domain.model.Restaurant;
import com.github.matheusferreiral.algafoodapi.domain.service.KitchenService;
import com.github.matheusferreiral.algafoodapi.domain.service.RestaurantService;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

  @Autowired private RestaurantService restaurantService;
  @Autowired private KitchenService kitchenService;

  @GetMapping
  public List<Restaurant> list() {
    return restaurantService.list();
  }

  @GetMapping("/{kitchenId}")
  public ResponseEntity<?> findById(@PathVariable Long kitchenId) {
    try {
      Restaurant restaurant = restaurantService.findById(kitchenId);
      return ResponseEntity.status(HttpStatus.OK).body(restaurant);

    } catch (EntityNotFoundException entityNotFoundException) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());
    }
  }

  @PostMapping
  public ResponseEntity<?> add(@RequestBody Restaurant restaurant) {
    try {
      restaurant = restaurantService.save(restaurant);

      return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);

    } catch (EntityNotFoundException entityNotFoundException) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(entityNotFoundException.getMessage());
    }
  }

  @PutMapping("/{restaurantId}")
  public ResponseEntity<?> update(
      @PathVariable Long restaurantId, @RequestBody Restaurant restaurant) {
    try {
      Restaurant currentRestaurant = restaurantService.findById(restaurantId);

      BeanUtils.copyProperties(restaurant, currentRestaurant, "id");

      currentRestaurant = restaurantService.save(currentRestaurant);
      return ResponseEntity.status(HttpStatus.OK).body(currentRestaurant);
    } catch (EntityNotFoundException entityNotFoundException) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());
    }
  }

  @DeleteMapping("{restaurantId}")
  public ResponseEntity<?> remove(@PathVariable Long restaurantId) {
    try {
      restaurantService.remove(restaurantId);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (EntityNotFoundException entityNotFoundException) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());
    }
  }
}
