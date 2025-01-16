package com.github.matheusferreiral.algafoodapi.api;

import com.github.matheusferreiral.algafoodapi.domain.exception.EntityNotFoundException;
import com.github.matheusferreiral.algafoodapi.domain.model.Restaurant;
import com.github.matheusferreiral.algafoodapi.domain.service.RestaurantService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

  @Autowired private RestaurantService restaurantService;

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
}
