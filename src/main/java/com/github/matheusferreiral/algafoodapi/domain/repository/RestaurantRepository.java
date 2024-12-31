package com.github.matheusferreiral.algafoodapi.domain.repository;

import com.github.matheusferreiral.algafoodapi.domain.model.Restaurant;
import java.util.List;

public interface RestaurantRepository {

  List<Restaurant> list();

  Restaurant findById(Long id);

  Restaurant save(Restaurant restaurant);

  void remove(Restaurant restaurant);
}
