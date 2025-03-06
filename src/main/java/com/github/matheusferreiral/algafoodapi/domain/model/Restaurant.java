package com.github.matheusferreiral.algafoodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "restaurant")
public class Restaurant {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "shipping_fee", nullable = false)
  private BigDecimal shippingFee;

  @ManyToOne
  // The join column has the same effect as the column, but we use this here due to being foreign
  // key
  @JoinColumn(name = "kitchen_id", nullable = false)
  private Kitchen kitchen;

  // This code defines a many-to-many relationship between the Restaurant and PaymentMethod
  // entities.
  // The @ManyToMany annotation indicates that a restaurant can have multiple payment methods and a
  // payment method can be used by multiple restaurants.
  // The @JoinTable annotation specifies the join table that maps the many-to-many relationship.
  // The 'name' attribute of @JoinTable specifies the name of the join table
  // ("restaurant_payment_method").
  // The 'joinColumns' attribute specifies the foreign key column in the join table that references
  // the Restaurant entity ("restaurant_id").
  // The 'inverseJoinColumns' attribute specifies the foreign key column in the join table that
  // references the PaymentMethod entity ("payment_method_id").
  @JsonIgnore
  @ManyToMany
  @JoinTable(
      name = "restaurant_payment_method",
      joinColumns = @JoinColumn(name = "restaurant_id"),
      inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
  private List<PaymentMethod> paymentMethods = new ArrayList<>();

  @Embedded private Address address;
}
