package com.github.matheusferreiral.algafoodapi.domain.repository;

import com.github.matheusferreiral.algafoodapi.domain.model.Kitchen;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {
  List<Kitchen> findAllByName(String name);
  
  Optional<Kitchen> findByName(String name);
}
