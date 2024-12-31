package com.github.matheusferreiral.algafoodapi.infrastructure.repository;

import com.github.matheusferreiral.algafoodapi.domain.model.Kitchen;
import com.github.matheusferreiral.algafoodapi.domain.repository.KitchenRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public class KitchenRepositoryImpl implements KitchenRepository {

  @PersistenceContext private EntityManager manager;

  @Override
  public List<Kitchen> list() {
    TypedQuery<Kitchen> query = manager.createQuery("from kitchen", Kitchen.class);

    return query.getResultList();
  }

  @Override
  public Kitchen findById(Long id) {
    return manager.find(Kitchen.class, id);
  }

  @Transactional
  @Override
  public Kitchen save(Kitchen kitchen) {
    return manager.merge(kitchen);
  }

  @Transactional
  @Override
  public void remove(Kitchen kitchen) {
    kitchen = findById(kitchen.getId());
    manager.remove(kitchen);
  }
}
