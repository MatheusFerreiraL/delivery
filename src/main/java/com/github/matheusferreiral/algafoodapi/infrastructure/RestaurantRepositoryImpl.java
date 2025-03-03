package com.github.matheusferreiral.algafoodapi.infrastructure;

import com.github.matheusferreiral.algafoodapi.domain.model.Restaurant;
import com.github.matheusferreiral.algafoodapi.domain.repository.RestaurantRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

  @PersistenceContext private EntityManager manager;

  @Override
  public List<Restaurant> find(
      String name, BigDecimal initialShippingFee, BigDecimal finalShippingFee) {
    var jpql =
        "from Restaurant where name like :name and shippingFee between :initialShippingFee and :finalShippingFee";
    return manager
        .createQuery(jpql, Restaurant.class)
        .setParameter("name", "%" + name + "%" )
        .setParameter("initialShippingFee", initialShippingFee)
        .setParameter("finalShippingFee", finalShippingFee)
        .getResultList();
  }
}
