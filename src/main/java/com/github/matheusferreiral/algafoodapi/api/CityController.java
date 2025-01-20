package com.github.matheusferreiral.algafoodapi.api;

import com.github.matheusferreiral.algafoodapi.domain.exception.EntityNotFoundException;
import com.github.matheusferreiral.algafoodapi.domain.model.City;
import com.github.matheusferreiral.algafoodapi.domain.service.CityService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController {
  @Autowired private CityService cityService;

  @GetMapping
  public List<City> list() {
    return cityService.list();
  }

  @GetMapping("/{cityId}")
  public ResponseEntity<?> findById(@PathVariable Long cityId) {
    try {
      City city = cityService.findById(cityId);
      return ResponseEntity.status(HttpStatus.OK).body(city);
    } catch (EntityNotFoundException entityNotFoundException) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());
    }
  }

  @PostMapping
  public ResponseEntity<?> add(@RequestBody City city) {
    try {
      city = cityService.save(city);
      return ResponseEntity.status(HttpStatus.CREATED).body(city);
    } catch (EntityNotFoundException | DataIntegrityViolationException entityNotFoundException) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(entityNotFoundException.getMessage());
    }
  }

  @PutMapping("/{cityId}")
  public ResponseEntity<?> update(@PathVariable Long cityId, @RequestBody City city) {
    try {
      City currentCity = cityService.findById(cityId);
      BeanUtils.copyProperties(city, currentCity, "id");
      currentCity = cityService.save(currentCity);
      return ResponseEntity.status(HttpStatus.OK).body(currentCity);
    } catch (EntityNotFoundException entityNotFoundException) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());
    }
  }

  @DeleteMapping("/{cityId}")
  public ResponseEntity<?> remove(@PathVariable Long cityId) {
    try {
      cityService.remove(cityId);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (EntityNotFoundException entityNotFoundException) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());
    }
  }
}
