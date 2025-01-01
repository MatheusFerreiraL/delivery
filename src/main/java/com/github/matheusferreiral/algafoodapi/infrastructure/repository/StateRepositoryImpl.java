package com.github.matheusferreiral.algafoodapi.infrastructure.repository;

import com.github.matheusferreiral.algafoodapi.domain.model.State;
import com.github.matheusferreiral.algafoodapi.domain.repository.StateRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
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
  public State findByid(Long id) {
    return manager.find(State.class, id);
  }

  @Transactional
  @Override
  public State save(State state) {
    return manager.merge(state);
  }

  @Transactional
  @Override
  public void remove(State state) {
    state = findByid(state.getId());
    manager.remove(state);
  }
}
