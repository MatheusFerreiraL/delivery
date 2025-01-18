package com.github.matheusferreiral.algafoodapi.domain.service;

import com.github.matheusferreiral.algafoodapi.domain.exception.EntityNotFoundException;
import com.github.matheusferreiral.algafoodapi.domain.model.Kitchen;
import com.github.matheusferreiral.algafoodapi.domain.model.Restaurant;
import com.github.matheusferreiral.algafoodapi.domain.repository.KitchenRepository;
import com.github.matheusferreiral.algafoodapi.domain.repository.RestaurantRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

  @Autowired private RestaurantRepository restaurantRepository;
  @Autowired private KitchenRepository kitchenRepository;

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

  public Restaurant save(Restaurant restaurant) {
    Long kitchenId = restaurant.getKitchen().getId();
    Kitchen kitchen = kitchenRepository.findById(kitchenId);

    if (kitchen == null) {
      throw new EntityNotFoundException(
          String.format(
              "Kithchen under code << %d >> was NOT found. Please, try " + "again!", kitchenId));
    }

    restaurant.setKitchen(kitchen);
    return restaurantRepository.save(restaurant);
  }

  public void remove(Long restaurantId) {
    try {
      restaurantRepository.remove(restaurantId);
    } catch (EntityNotFoundException entityNotFoundException) {
      throw new EntityNotFoundException(
          String.format(
              "There is NOT a restaurant registered under the code << %d >>. "
                  + "Please try again!",
              restaurantId));
    }
  }
}
