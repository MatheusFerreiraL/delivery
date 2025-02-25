package com.github.matheusferreiral.algafoodapi.domain.repository;

import com.github.matheusferreiral.algafoodapi.domain.model.Restaurant;
import java.math.BigDecimal;
import java.util.List;
import java.util.ListIterator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
  List<Restaurant> findByShippingFeeBetween(BigDecimal initialFee, BigDecimal finalFee);

  List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchenId);
  
  List<Restaurant> findFirstByNameContaining(String name);
   
  List<Restaurant> findTop2ByNameContaining(String name);
  
  boolean existsByName(String name);
}
