package com.github.matheusferreiral.algafoodapi.domain.repository;

import com.github.matheusferreiral.algafoodapi.domain.model.PaymentMethod;
import java.util.List;

public interface PaymentMethodRepository {

  List<PaymentMethod> list();

  PaymentMethod findById(Long id);

  PaymentMethod save(PaymentMethod paymentMethod);

  void remove(PaymentMethod paymentMethod);
}
