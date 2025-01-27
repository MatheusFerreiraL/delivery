package com.github.matheusferreiral.algafoodapi.domain.service;

import com.github.matheusferreiral.algafoodapi.domain.exception.EntityNotFoundException;
import com.github.matheusferreiral.algafoodapi.domain.model.City;
import com.github.matheusferreiral.algafoodapi.domain.model.State;
import com.github.matheusferreiral.algafoodapi.domain.repository.CityRepository;
import com.github.matheusferreiral.algafoodapi.domain.repository.StateRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CityService {
  @Autowired private CityRepository cityRepository;
  @Autowired private StateRepository stateRepository;

  public List<City> list() {
    return cityRepository.findAll();
  }

  public Optional<City> findById(Long cityId) {
    try {
      return cityRepository.findById(cityId);
    } catch (EntityNotFoundException entityNotFoundException) {
      throw new EntityNotFoundException(
          String.format("City under code << %d >> was NOT found! :(", cityId));
    }
  }

  public City save(City city) {
    Long stateId = city.getState().getId();
    try {
      State state = stateRepository.findById(stateId);
      city.setState(state);
      return cityRepository.save(city);
    } catch (DataIntegrityViolationException dataIntegrityViolationException) {
      throw new DataIntegrityViolationException(
          "This operation could NOT be continued :( Please, try again! [E-DIVE-CS-001]");
    } catch (EntityNotFoundException entityNotFoundException) {
      throw new EntityNotFoundException(
          String.format(
              "State under code << %d >> was NOT found. :( Please, try " + "again!", stateId));
    }
  }

  public void remove(Long cityId) {
    cityRepository
        .findById(cityId)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    String.format(
                        "There is NOT a city registered under the code << %d >> :( Please, try again",
                        cityId)));
    cityRepository.deleteById(cityId);
  }
}
