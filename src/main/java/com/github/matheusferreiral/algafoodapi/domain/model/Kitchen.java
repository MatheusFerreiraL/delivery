package com.github.matheusferreiral.algafoodapi.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
// This annotation is optional, but it is a good practice to use it. If we don't
// use it, the table name will be the class name in lowercase.
@Table(name = "kitchen")
public class Kitchen {

  @EqualsAndHashCode.Include
  @Id
  //  The GenerationType.IDENTITY strategy is used to generate an AutoIncremented primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // This annotation is optional, but it is a good practice to use it. If we don't
  // use it, the column name will be the attribute name.
  @Column(name = "name", nullable = false)
  private String name;
}
