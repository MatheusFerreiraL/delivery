package com.github.matheusferreiral.algafoodapi.api;

import com.github.matheusferreiral.algafoodapi.domain.exception.EntityInUseException;
import com.github.matheusferreiral.algafoodapi.domain.exception.EntityNotFoundException;
import com.github.matheusferreiral.algafoodapi.domain.model.State;
import com.github.matheusferreiral.algafoodapi.domain.service.StateService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/states")
public class StateController {

  @Autowired private StateService stateService;

  @GetMapping
  public List<State> list() {
    return stateService.list();
  }

  @GetMapping("/{stateId}")
  public ResponseEntity<?> findById(@PathVariable Long stateId) {
      Optional<State> state = stateService.findById(stateId);
      if(state.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("State under code << %d >> not found!",
            stateId));
    }
      return ResponseEntity.status(HttpStatus.OK).body(state);
  }

  @PostMapping
  public ResponseEntity<?> add(@RequestBody State state) {
    try {
      state = stateService.save(state);
      return ResponseEntity.status(HttpStatus.CREATED).body(state);
    } catch (EntityNotFoundException | DataIntegrityViolationException entityNotFoundException) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(entityNotFoundException.getMessage());
    }
  }

  @PutMapping("/{stateId}")
  public ResponseEntity<?> update(@PathVariable Long stateId, @RequestBody State state) {
    Optional<State> optionalState = stateService.findById(stateId);
    if (optionalState.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(String.format("State under code << %d >> not found!", stateId));
    }

    try {
      State currentState = optionalState.get();
      BeanUtils.copyProperties(state, currentState, "id");
      currentState = stateService.save(currentState);
      return ResponseEntity.status(HttpStatus.OK).body(currentState);
    } catch (EntityNotFoundException entityNotFoundException) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());
    } catch (DataIntegrityViolationException dataIntegrityViolationException) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(dataIntegrityViolationException.getMessage());
    }
  }

  @DeleteMapping("/{stateId}")
  public ResponseEntity<?> remove(@PathVariable Long stateId) {
    try {
      stateService.remove(stateId);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (EntityNotFoundException entityNotFoundException) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());
    } catch (EntityInUseException entityInUseException) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(entityInUseException.getMessage());
    }
  }
}
