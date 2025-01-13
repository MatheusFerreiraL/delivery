package com.github.matheusferreiral.algafoodapi.domain.service;

import com.github.matheusferreiral.algafoodapi.domain.exception.EntityInUseException;
import com.github.matheusferreiral.algafoodapi.domain.exception.EntityNotFoundException;
import com.github.matheusferreiral.algafoodapi.domain.model.Kitchen;
import com.github.matheusferreiral.algafoodapi.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class KitchenService {

  @Autowired private KitchenRepository kitchenRepository;

  // the 'save' method works for both creating and updating kitchens
  public Kitchen save(Kitchen kitchen) {
    return kitchenRepository.save(kitchen);
  }

  public void remove(Long kitchenId) {
    try {
      kitchenRepository.remove(kitchenId);

    } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
      throw new EntityNotFoundException(
          String.format("There is NOT a kitchen registered under the code << %d >>", kitchenId));

    } catch (DataIntegrityViolationException dataIntegrityViolationException) {
      throw new EntityInUseException(
          String.format(
              "Kitchen under ID << %d >> could NOT be removed because it is still " + "being used",
              kitchenId));
    }
  }
}
