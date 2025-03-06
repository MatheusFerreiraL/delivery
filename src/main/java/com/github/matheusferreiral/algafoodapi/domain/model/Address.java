package com.github.matheusferreiral.algafoodapi.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// The @Embeddable annotation is used to indicate that this class can be embedded in other entities.
// In the context of JPA, this means that the fields of the Address class will be mapped as part of
// the table of the entity that embeds it, rather than being mapped to a separate table.
@Embeddable
public class Address {

  @Column(name = "address_street")
  private String street;

  @Column(name = "address_city")
  private String city;

  @Column(name = "address_state")
  private String state;

  @Column(name = "address_zip")
  private String zip;
}
