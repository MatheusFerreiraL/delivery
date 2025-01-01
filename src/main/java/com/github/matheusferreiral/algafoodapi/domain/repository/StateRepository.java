package com.github.matheusferreiral.algafoodapi.domain.repository;

import com.github.matheusferreiral.algafoodapi.domain.model.State;
import java.util.List;

public interface StateRepository {

  List<State> list();

  State findByid(Long id);

  State save(State state);

  void remove(State state);
}
