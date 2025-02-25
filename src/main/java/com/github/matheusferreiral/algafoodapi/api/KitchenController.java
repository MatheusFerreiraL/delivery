package com.github.matheusferreiral.algafoodapi.api;

import com.github.matheusferreiral.algafoodapi.domain.exception.EntityInUseException;
import com.github.matheusferreiral.algafoodapi.domain.exception.EntityNotFoundException;
import com.github.matheusferreiral.algafoodapi.domain.model.Kitchen;
import com.github.matheusferreiral.algafoodapi.domain.repository.KitchenRepository;
import com.github.matheusferreiral.algafoodapi.domain.service.KitchenService;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // include @ResponseBody & @Controller annotations
@RequestMapping(value = "/kitchens")
public class KitchenController {

  @Autowired private KitchenService kitchenService;
  @Autowired private KitchenRepository kitchenRepository;

  @GetMapping
  public List<Kitchen> list() {
    return kitchenService.list();
  }

  @GetMapping("/{kitchenId}") // The '{kitchenId}' is called path variable, and can receive any name
  public ResponseEntity<?> findById(@PathVariable Long kitchenId) {
    Optional<Kitchen> kitchen = kitchenService.findById(kitchenId);

    if (kitchen.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(String.format("Kitchen under code << %d >> not found!", kitchenId));
    }
    return ResponseEntity.status(HttpStatus.OK).body(kitchen);
  }

  @PostMapping
  //  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> add(@RequestBody Kitchen kitchen) {
    try {
      return ResponseEntity.status(HttpStatus.CREATED).body(kitchenService.save(kitchen));
    } catch (DataIntegrityViolationException dataIntegrityViolationException) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(dataIntegrityViolationException.getMessage());
    }
  }

  // NOTE: Missing the treatment for @kitchen being null
  @PutMapping("/{kitchenId}")
  public ResponseEntity<?> update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
    Optional<Kitchen> optionalKitchen = kitchenService.findById(kitchenId);

    if (optionalKitchen.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(String.format("Kitchen under code << %d >> not found!", kitchenId));
    }

    try {
      Kitchen currentKitchen = optionalKitchen.get();
      // from the third parameter onwards, are properties that will be ignored on the 'copy & paste'
      BeanUtils.copyProperties(kitchen, currentKitchen, "id");
      currentKitchen = kitchenService.save(currentKitchen);
      return ResponseEntity.status(HttpStatus.OK).body(currentKitchen);
    } catch (DataIntegrityViolationException dataIntegrityViolationException) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(dataIntegrityViolationException.getMessage());
    }
  }

  @DeleteMapping("/{kitchenId}")
  public ResponseEntity<?> remove(@PathVariable Long kitchenId) {

    try {
      kitchenService.remove(kitchenId);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    } catch (EntityNotFoundException entityNotFoundException) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());

    } catch (EntityInUseException entityInUseException) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(
              entityInUseException
                  .getMessage()); // Conflict is code 409. Using the 400 would be correct as well,
      // but it is less specific
    }
  }

  @GetMapping("/by-name")
  public List<Kitchen> kitchensByName(@RequestParam String name) {
    return kitchenRepository.findAllByName(name);
  }

  @GetMapping("/by-unique-name")
  public Optional<Kitchen> uniqueKitchenByName(@RequestParam String name) {
    return kitchenRepository.findByName(name);
  }

  @GetMapping("/by-name-containing")
  public List<Kitchen> kitchensByNameContaining(@RequestParam String name) {
    return kitchenRepository.findAllByNameContaining(name);
  }
}
