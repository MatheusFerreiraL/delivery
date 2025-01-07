package com.github.matheusferreiral.algafoodapi.api;

import com.github.matheusferreiral.algafoodapi.domain.model.Kitchen;
import com.github.matheusferreiral.algafoodapi.domain.repository.KitchenRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}
