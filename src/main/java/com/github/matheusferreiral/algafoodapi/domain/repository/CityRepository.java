package com.github.matheusferreiral.algafoodapi.domain.repository;

import com.github.matheusferreiral.algafoodapi.domain.model.City;
import java.util.List;

public interface CityRepository {

  List<City> list();

  City findById(Long id);

  City save(City city);

  void remove(City city);
}
