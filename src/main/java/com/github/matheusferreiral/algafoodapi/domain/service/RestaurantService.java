package com.github.matheusferreiral.algafoodapi.domain.service;

import com.github.matheusferreiral.algafoodapi.domain.exception.EntityNotFoundException;
import com.github.matheusferreiral.algafoodapi.domain.model.Restaurant;
import com.github.matheusferreiral.algafoodapi.domain.repository.RestaurantRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

  @Autowired private RestaurantRepository restaurantRepository;

  public List<Restaurant> list() {
    return restaurantRepository.list();
  }

  public Restaurant findById(Long restaurantId) {
    try {
      return restaurantRepository.findById(restaurantId);

    } catch (EntityNotFoundException entityNotFoundException) {
      throw new EntityNotFoundException(
          String.format("Restaurant under code << %d >> was NOT found! :(", restaurantId));
    }
  }
}
