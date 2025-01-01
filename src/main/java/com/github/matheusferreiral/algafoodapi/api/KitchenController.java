package com.github.matheusferreiral.algafoodapi.api;

import com.github.matheusferreiral.algafoodapi.domain.model.Kitchen;
import com.github.matheusferreiral.algafoodapi.domain.repository.KitchenRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
  public Kitchen findById(@PathVariable Long kitchenId) {
    return kitchenRepository.findById(kitchenId);
  }
}
