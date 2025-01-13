package com.github.matheusferreiral.algafoodapi.domain.repository;

import com.github.matheusferreiral.algafoodapi.domain.model.Kitchen;
import java.util.List;

public interface KitchenRepository {
  List<Kitchen> list();

  Kitchen findById(Long id);

  Kitchen save(Kitchen kitchen);

  void remove(Long id);
}
