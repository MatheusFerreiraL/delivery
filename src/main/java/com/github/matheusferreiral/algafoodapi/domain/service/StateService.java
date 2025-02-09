package com.github.matheusferreiral.algafoodapi.domain.service;

import com.github.matheusferreiral.algafoodapi.domain.exception.EntityInUseException;
import com.github.matheusferreiral.algafoodapi.domain.exception.EntityNotFoundException;
import com.github.matheusferreiral.algafoodapi.domain.model.State;
import com.github.matheusferreiral.algafoodapi.domain.repository.StateRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class StateService {

  @Autowired private StateRepository stateRepository;

  public List<State> list() {
    return stateRepository.findAll();
  }

  public Optional<State> findById(Long stateId) {
    try {
      return stateRepository.findById(stateId);
    } catch (EntityNotFoundException entityNotFoundException) {
      throw new EntityNotFoundException(
          String.format("State under code << %d >> was NOT found :( Please, try again!", stateId));
    }
  }

  public State save(State state) {
    try {
      state = stateRepository.save(state);
      return state;
    } catch (DataIntegrityViolationException dataIntegrityViolationException) {
      throw new DataIntegrityViolationException(
          "This operation could NOT be continued :( Please, try again! [E-DIVE-SS-001]");
    } catch (EntityNotFoundException entityNotFoundException) {
      throw new EntityNotFoundException(
          String.format(
              "State under code << %d >> was NOT found. :( Please, try " + "again!",
              state.getId()));
    }
  }

  public void remove(Long stateId) {
    stateRepository
        .findById(stateId)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    String.format(
                        "State under code << %d >> was NOT found :( Please, try again!",
                        stateId)));
    try {
      stateRepository.deleteById(stateId);
    } catch (EntityNotFoundException entityNotFoundException) {
      throw new EntityNotFoundException(
          String.format("State under code << %d >> was NOT found :( Please, try again!", stateId));
    } catch (DataIntegrityViolationException dataIntegrityViolationException) {
      throw new EntityInUseException(
          String.format(
              "State under code << %d >> could NOT be removed because it is "
                  + "still being used. Try again later!",
              stateId));
    }
  }
}
