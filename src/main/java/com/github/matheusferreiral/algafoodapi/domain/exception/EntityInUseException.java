package com.github.matheusferreiral.algafoodapi.domain.exception;

public class EntityInUseException extends RuntimeException {
  private static final Long serialVersionUUID = 1L;
  
  public EntityInUseException(String message) {
    super(message);
  }
}
