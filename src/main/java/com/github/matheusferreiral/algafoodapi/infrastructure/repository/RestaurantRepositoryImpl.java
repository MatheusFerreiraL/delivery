package com.github.matheusferreiral.algafoodapi.infrastructure.repository;

import com.github.matheusferreiral.algafoodapi.domain.exception.EntityNotFoundException;
import com.github.matheusferreiral.algafoodapi.domain.model.Restaurant;
import com.github.matheusferreiral.algafoodapi.domain.repository.RestaurantRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

  @PersistenceContext EntityManager manager;

  @Override
  public List<Restaurant> list() {
    TypedQuery<Restaurant> query = manager.createQuery("from Restaurant", Restaurant.class);

    return query.getResultList();
  }

  @Override
  public Restaurant findById(Long id) {
    Restaurant restaurant = manager.find(Restaurant.class, id);

    if (restaurant == null) {
      throw new EntityNotFoundException("Restaurant Entity not found!");
    }
    return restaurant;
  }

  @Transactional
  @Override
  public Restaurant save(Restaurant restaurant) {
    return manager.merge(restaurant);
  }

  @Override
  public void remove(Restaurant restaurant) {
    restaurant = findById(restaurant.getId());
    manager.remove(restaurant);
  }
}
