package com.github.matheusferreiral.algafoodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "order")
public class Order {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "subtotal", nullable = false)
  private BigDecimal subTotal;

  @Column(name = "shipping_fee", nullable = false)
  private BigDecimal shippingFee;

  @Column(name = "total", nullable = false)
  private BigDecimal total;

  @JsonIgnore
  @CreationTimestamp
  @Column(nullable = false, columnDefinition = "datetime")
  private LocalDateTime createdAt;

  @Column(columnDefinition = "datetime")
  private LocalDateTime confirmedAt;

  @Column(columnDefinition = "datetime")
  private LocalDateTime cancelledAt;

  @Column(columnDefinition = "datetime")
  private LocalDateTime completedAt;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User customer;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Restaurant restaurant;

  @ManyToOne
  @JoinColumn(nullable = false)
  private PaymentMethod paymentMethod;

  @JsonIgnore @Embedded private Address shippingAddress;

  @OneToMany(mappedBy = "order")
  private List<OrderItem> orderItems;

  @Column(name = "status")
  private OrderStatus orderStatus;
}
