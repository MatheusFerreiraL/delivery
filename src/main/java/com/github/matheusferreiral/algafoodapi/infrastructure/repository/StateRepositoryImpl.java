package com.github.matheusferreiral.algafoodapi.infrastructure.repository;

import com.github.matheusferreiral.algafoodapi.domain.exception.EntityNotFoundException;
import com.github.matheusferreiral.algafoodapi.domain.model.State;
import com.github.matheusferreiral.algafoodapi.domain.repository.StateRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StateRepositoryImpl implements StateRepository {

  @PersistenceContext EntityManager manager;

  @Override
  public List<State> list() {
    return manager.createQuery("from State", State.class).getResultList();
  }

  @Override
  public State findById(Long id) {
    State state = manager.find(State.class, id);

    if (state == null) {
      throw new EntityNotFoundException("State entity not found");
    }
    return state;
  }

  @Transactional
  @Override
  public State save(State state) {
    return manager.merge(state);
  }

  @Transactional
  @Override
  public void remove(Long id) {
    State state = findById(id);

    if (state == null) {
      throw new EmptyResultDataAccessException(1);
    }
    manager.remove(state);
  }
}
