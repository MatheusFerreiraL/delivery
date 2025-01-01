package com.github.matheusferreiral.algafoodapi.api;

import com.github.matheusferreiral.algafoodapi.domain.model.State;
import com.github.matheusferreiral.algafoodapi.domain.repository.StateRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/states")
public class StateController {
  
  @Autowired
  private StateRepository stateRepository;
  
  @GetMapping
  public List<State> list() {
    return stateRepository.list();
  }
}
