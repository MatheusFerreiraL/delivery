package com.github.matheusferreiral.algafoodapi.domain.repository;

import com.github.matheusferreiral.algafoodapi.domain.model.Restaurant;
import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQueries {

  List<Restaurant> find(String name, BigDecimal initialShippingFee, BigDecimal finalShippingFee);
}
