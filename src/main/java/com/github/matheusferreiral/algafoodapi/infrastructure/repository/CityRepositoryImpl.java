package com.github.matheusferreiral.algafoodapi.infrastructure.repository;

import com.github.matheusferreiral.algafoodapi.domain.model.City;
import com.github.matheusferreiral.algafoodapi.domain.repository.CityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public class CityRepositoryImpl implements CityRepository {

  @PersistenceContext EntityManager manager;

  @Override
  public List<City> list() {
    return manager.createQuery("from city", City.class).getResultList();
  }

  @Override
  public City findById(Long id) {
    return manager.find(City.class, id);
  }

  @Transactional
  @Override
  public City save(City city) {
    return manager.merge(city);
  }

  @Transactional
  @Override
  public void remove(City city) {
    city = findById(city.getId());
    manager.remove(city);
  }
}
