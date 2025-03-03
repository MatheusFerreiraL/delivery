package com.github.matheusferreiral.algafoodapi.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.matheusferreiral.algafoodapi.domain.exception.EntityNotFoundException;
import com.github.matheusferreiral.algafoodapi.domain.model.Restaurant;
import com.github.matheusferreiral.algafoodapi.domain.repository.RestaurantRepository;
import com.github.matheusferreiral.algafoodapi.domain.service.RestaurantService;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

  @Autowired private RestaurantService restaurantService;
  @Autowired private RestaurantRepository restaurantRepository;

  @GetMapping
  public List<Restaurant> list() {
    return restaurantService.list();
  }

  @GetMapping("/{restaurantId}")
  public ResponseEntity<?> findById(@PathVariable Long restaurantId) {
    Optional<Restaurant> restaurant = restaurantService.findById(restaurantId);
    if (restaurant.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(String.format("Restaurant under code << %d >> not found!", restaurantId));
    }
    return ResponseEntity.status(HttpStatus.OK).body(restaurant);
  }

  @PostMapping
  public ResponseEntity<?> add(@RequestBody Restaurant restaurant) {
    try {
      restaurant = restaurantService.save(restaurant);

      return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);

    } catch (EntityNotFoundException | DataIntegrityViolationException entityNotFoundException) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(entityNotFoundException.getMessage());
    }
  }

  // NOTE: DataIntegrityViolation exception needs to be fixed!
  @PutMapping("/{restaurantId}")
  public ResponseEntity<?> update(
      @PathVariable Long restaurantId, @RequestBody Restaurant restaurant) {

    Optional<Restaurant> optionalRestaurant = restaurantService.findById(restaurantId);
    if (optionalRestaurant.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(String.format("Restaurant under code << %d >> not found!", restaurantId));
    }

    try {
      Restaurant currentRestaurant = optionalRestaurant.get();
      BeanUtils.copyProperties(restaurant, currentRestaurant, "id");

      currentRestaurant = restaurantService.save(currentRestaurant);
      return ResponseEntity.status(HttpStatus.OK).body(currentRestaurant);
    } catch (EntityNotFoundException | DataIntegrityViolationException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @PatchMapping("/{restaurantId}")
  public ResponseEntity<?> partialUpdate(
      @PathVariable Long restaurantId, @RequestBody Map<String, Object> fields) {
    Optional<Restaurant> optionalRestaurant = restaurantService.findById(restaurantId);
    if (optionalRestaurant.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(String.format("Restaurant under code << %d >> not found!", restaurantId));
    }
    try {
      Restaurant currentRestaurant = optionalRestaurant.get();
      merge(fields, currentRestaurant);
      return update(restaurantId, currentRestaurant);
    } catch (EntityNotFoundException entityNotFoundException) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());
    }
  }

  private static void merge(Map<String, Object> originFields, Restaurant finalRestaurant) {
    // The object mapper is responsible for dealing with the conversions of all the fields (data
    // types)
    ObjectMapper objectMapper = new ObjectMapper();
    Restaurant originRestaurant = objectMapper.convertValue(originFields, Restaurant.class);

    originFields.forEach(
        (propertyName, propertyValue) -> {
          Field field = ReflectionUtils.findField(Restaurant.class, propertyName);
          field.setAccessible(true);

          Object newValue = ReflectionUtils.getField(field, originRestaurant);

          ReflectionUtils.setField(field, finalRestaurant, newValue);
        });
  }

  @DeleteMapping("/{restaurantId}")
  public ResponseEntity<?> remove(@PathVariable Long restaurantId) {
    try {
      restaurantService.remove(restaurantId);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (EntityNotFoundException entityNotFoundException) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());
    }
  }

  @GetMapping("/by-shipping-fee")
  public List<Restaurant> findByShippingFeeBetween(
      @RequestParam BigDecimal initialFee, @RequestParam BigDecimal finalFee) {
    return restaurantRepository.findByShippingFeeBetween(initialFee, finalFee);
  }

  @GetMapping("/by-name-and-kitchenId")
  public List<Restaurant> findByNameAndKitchenId(
      @RequestParam String name, @RequestParam Long kitchenId) {
    return restaurantRepository.queryByName(name, kitchenId);
  }

  @GetMapping("/by-name-and-shipping-fee")
  public List<Restaurant> findByNameAndShippingFee(
      @RequestParam String name, @RequestParam BigDecimal initialFee, @RequestParam BigDecimal finalFee) {
    return restaurantRepository.find(name, initialFee, finalFee);
  }

  @GetMapping("/first-by-name")
  public List<Restaurant> findFirstByName(@RequestParam String name) {
    return restaurantRepository.findFirstByNameContaining(name);
  }

  @GetMapping("/top2-by-name")
  public List<Restaurant> findTop2ByName(@RequestParam String name) {
    return restaurantRepository.findTop2ByNameContaining(name);
  }
}
