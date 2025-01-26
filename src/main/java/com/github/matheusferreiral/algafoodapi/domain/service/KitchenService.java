package com.github.matheusferreiral.algafoodapi.domain.service;

import com.github.matheusferreiral.algafoodapi.domain.exception.EntityInUseException;
import com.github.matheusferreiral.algafoodapi.domain.exception.EntityNotFoundException;
import com.github.matheusferreiral.algafoodapi.domain.model.Kitchen;
import com.github.matheusferreiral.algafoodapi.domain.repository.KitchenRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class KitchenService {

  @Autowired private KitchenRepository kitchenRepository;

  public List<Kitchen> list() {
    return kitchenRepository.findAll();
  }

  public Optional<Kitchen> findById(Long kitchenId) {
    try {
      return kitchenRepository.findById(kitchenId);
    } catch (EntityNotFoundException entityNotFoundException) {
      throw new EntityNotFoundException(
          String.format("Kitchen under code << %d >> not found!", kitchenId));
    }
  }

  // the 'save' method works for both creating and updating kitchens
  public Kitchen save(Kitchen kitchen) {
    try {
      return kitchenRepository.save(kitchen);
    } catch (DataIntegrityViolationException dataIntegrityViolationException) {
      throw new DataIntegrityViolationException(
          "This operation could NOT be continued :( Please, try again! [E-DIVE-KS-001]");
    }
  }

  public void remove(Long kitchenId) {
    kitchenRepository
        .findById(kitchenId)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    String.format(
                        "There is NOT a kitchen registered under the code << %d >>", kitchenId)));

    try {
      kitchenRepository.deleteById(kitchenId);

    } catch (DataIntegrityViolationException dataIntegrityViolationException) {
      throw new EntityInUseException(
          String.format(
              "Kitchen under ID << %d >> could NOT be removed because it is still " + "being used",
              kitchenId));
    }
  }
}
