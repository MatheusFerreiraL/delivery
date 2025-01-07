package com.github.matheusferreiral.algafoodapi.api;

import com.github.matheusferreiral.algafoodapi.domain.model.Kitchen;
import com.github.matheusferreiral.algafoodapi.domain.repository.KitchenRepository;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController // include @ResponseBody & @Controller annotations
@RequestMapping(value = "/kitchens")
public class KitchenController {

  @Autowired private KitchenRepository kitchenRepository;

  @GetMapping
  public List<Kitchen> list() {
    return kitchenRepository.list();
  }

  @GetMapping("/{kitchenId}") // The '{kitchenId}' is called path variable, and can receive any name
  public ResponseEntity<?> findById(@PathVariable Long kitchenId) {
    Kitchen kitchen = kitchenRepository.findById(kitchenId);
    if (kitchen == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kitchen not found!");
    }
    return ResponseEntity.status(HttpStatus.OK).body(kitchen);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Kitchen add(@RequestBody Kitchen kitchen) {
    return kitchenRepository.save(kitchen);
  }

  @PutMapping("/{kitchenId}")
  public ResponseEntity<?> update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
    Kitchen currentKitchen = kitchenRepository.findById(kitchenId);

    if (currentKitchen == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kitchen not found! :/");
    }
    // from the third parameter onwards, are properties that will be ignored on the 'copy & paste'
    BeanUtils.copyProperties(kitchen, currentKitchen, "id");
    currentKitchen = kitchenRepository.save(currentKitchen);
    return ResponseEntity.status(HttpStatus.OK).body(currentKitchen);
  }

  @DeleteMapping("/{kitchenId}")
  public ResponseEntity<?> remove(@PathVariable Long kitchenId) {

    try {
      Kitchen kitchen = kitchenRepository.findById(kitchenId);

      if (kitchen == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kitchen not found! :/");
      }
      kitchenRepository.remove(kitchen);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    } catch (DataIntegrityViolationException dataIntegrityViolationException) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(
              dataIntegrityViolationException
                  .getMessage()); // Conflict is code 409. Using the 400 would be correct as well,
                                  // but it is less specific
    }
  }
}
