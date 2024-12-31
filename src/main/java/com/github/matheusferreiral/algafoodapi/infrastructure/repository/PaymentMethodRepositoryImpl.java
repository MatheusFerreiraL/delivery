package com.github.matheusferreiral.algafoodapi.infrastructure.repository;

import com.github.matheusferreiral.algafoodapi.domain.model.PaymentMethod;
import com.github.matheusferreiral.algafoodapi.domain.repository.PaymentMethodRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {

  @PersistenceContext EntityManager manager;

  @Override
  public List<PaymentMethod> list() {
    return manager.createQuery("from PaymentMethod", PaymentMethod.class).getResultList();
  }

  @Override
  public PaymentMethod findById(Long id) {
    return manager.find(PaymentMethod.class, id);
  }

  @Override
  public PaymentMethod save(PaymentMethod paymentMethod) {
    return manager.merge(paymentMethod);
  }

  @Override
  public void remove(PaymentMethod paymentMethod) {
    paymentMethod = findById(paymentMethod.getId());
    manager.remove(paymentMethod);
  }
}
