package com.github.matheusferreiral.algafoodapi.domain.service;

import com.github.matheusferreiral.algafoodapi.domain.exception.EntityInUseException;
import com.github.matheusferreiral.algafoodapi.domain.exception.EntityNotFoundException;
import com.github.matheusferreiral.algafoodapi.domain.model.Kitchen;
import com.github.matheusferreiral.algafoodapi.domain.repository.KitchenRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class KitchenService {

  @Autowired private KitchenRepository kitchenRepository;

  public List<Kitchen> list() {
    return kitchenRepository.list();
  }

  public Kitchen findById(Long kitchenId) {
    try {
      Kitchen kitchen = kitchenRepository.findById(kitchenId);
      return kitchen;
    } catch (EntityNotFoundException entityNotFoundException) {
      throw new EntityNotFoundException(
          String.format("Kitchen under code << %d >> not found!", kitchenId));
    }
  }

  // the 'save' method works for both creating and updating kitchens
  public Kitchen save(Kitchen kitchen) {
    return kitchenRepository.save(kitchen);
  }

  public void remove(Long kitchenId) {
    try {
      kitchenRepository.remove(kitchenId);

    } catch (EntityNotFoundException entityNotFoundException) {
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
