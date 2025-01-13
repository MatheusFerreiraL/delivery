package com.github.matheusferreiral.algafoodapi.domain.exception;

public class EntityNotFoundException extends RuntimeException {
  private static final Long serialVersionUUID = 2L;

  public EntityNotFoundException(String message) {
    super(message);
  }
}
