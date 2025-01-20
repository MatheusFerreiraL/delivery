package com.github.matheusferreiral.algafoodapi.infrastructure.repository;

import com.github.matheusferreiral.algafoodapi.domain.exception.EntityNotFoundException;
import com.github.matheusferreiral.algafoodapi.domain.model.City;
import com.github.matheusferreiral.algafoodapi.domain.repository.CityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CityRepositoryImpl implements CityRepository {

  @PersistenceContext EntityManager manager;

  @Override
  public List<City> list() {
    return manager.createQuery("from City", City.class).getResultList();
  }

  @Override
  public City findById(Long id) {
    City city = manager.find(City.class, id);

    if (city == null) {
      throw new EntityNotFoundException("City entity not found");
    }
    return city;
  }

  @Transactional
  @Override
  public City save(City city) {
    return manager.merge(city);
  }

  @Transactional
  @Override
  public void remove(Long cityId) {
    City city = findById(cityId);

    if (city == null) {
      throw new EmptyResultDataAccessException(1);
    }
    manager.remove(city);
  }
}
