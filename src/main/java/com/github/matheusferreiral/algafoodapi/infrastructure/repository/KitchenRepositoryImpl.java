package com.github.matheusferreiral.algafoodapi.infrastructure.repository;

import com.github.matheusferreiral.algafoodapi.domain.exception.EntityNotFoundException;
import com.github.matheusferreiral.algafoodapi.domain.model.Kitchen;
import com.github.matheusferreiral.algafoodapi.domain.repository.KitchenRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class KitchenRepositoryImpl implements KitchenRepository {

  @PersistenceContext private EntityManager manager;

  @Override
  public List<Kitchen> list() {
    TypedQuery<Kitchen> query = manager.createQuery("from Kitchen", Kitchen.class);

    return query.getResultList();
  }

  @Override
  public Kitchen findById(Long id) {
    Kitchen kitchen = manager.find(Kitchen.class, id);

//    if (kitchen == null) {
//      throw new EntityNotFoundException("Kitchen Entity not found");
//    }

    return kitchen;
  }

  @Transactional
  @Override
  public Kitchen save(Kitchen kitchen) {
    return manager.merge(kitchen);
  }

  @Transactional
  @Override
  public void remove(Long id) {
    Kitchen kitchen = findById(id);

    if (kitchen == null) {
      throw new EmptyResultDataAccessException(1);
    }
    manager.remove(kitchen);
  }
}
