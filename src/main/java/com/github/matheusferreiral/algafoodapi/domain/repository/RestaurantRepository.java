package com.github.matheusferreiral.algafoodapi.domain.repository;

import com.github.matheusferreiral.algafoodapi.domain.model.Restaurant;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
  List<Restaurant> findByShippingFeeBetween(BigDecimal initialFee, BigDecimal finalFee);

  // This method is going to substitute the "findByNameContainingAndKitchenId"
  //  @Param used only to bind the parameter passed in the function with the one used on the query
  @Query("from Restaurant where name like %:name% and kitchen.id = :id")
  List<Restaurant> queryByName(String name, @Param("id") Long kitchenId);

  //  List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchenId);

  List<Restaurant> findFirstByNameContaining(String name);

  List<Restaurant> findTop2ByNameContaining(String name);

  boolean existsByName(String name);
}
