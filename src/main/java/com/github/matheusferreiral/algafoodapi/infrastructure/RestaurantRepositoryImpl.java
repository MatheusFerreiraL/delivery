package com.github.matheusferreiral.algafoodapi.infrastructure;

import com.github.matheusferreiral.algafoodapi.domain.model.Restaurant;
import com.github.matheusferreiral.algafoodapi.domain.repository.RestaurantRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

  @PersistenceContext private EntityManager manager;

  @Override
  public List<Restaurant> find(
      String name, BigDecimal initialShippingFee, BigDecimal finalShippingFee) {
    var jpql = new StringBuilder();
    jpql.append("from Restaurant where 1 = 1");

    var parameters = new HashMap<String, Object>();

    if (StringUtils.hasLength(name)) {
      jpql.append(" and name like :name");
      parameters.put("name", "%" + name + "%");
    }

    if (initialShippingFee != null) {
      jpql.append(" and shippingFee >= :initialShippingFee");
      parameters.put("initialShippingFee", initialShippingFee);
    }

    if (finalShippingFee != null) {
      jpql.append(" and shippingFee <= :finalShippingFee");
      parameters.put("finalShippingFee", finalShippingFee);
    }

    TypedQuery<Restaurant> query = manager.createQuery(jpql.toString(), Restaurant.class);
    parameters.forEach(query::setParameter);

    return query.getResultList();
  }
}