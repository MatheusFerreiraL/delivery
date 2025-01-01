package com.github.matheusferreiral.algafoodapi.infrastructure.repository;

import com.github.matheusferreiral.algafoodapi.domain.model.Permission;
import com.github.matheusferreiral.algafoodapi.domain.repository.PermissionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PermissionRepositoryImpl implements PermissionRepository {

  @PersistenceContext EntityManager manager;

  @Override
  public List<Permission> list() {
    return manager.createQuery("from Permission", Permission.class).getResultList();
  }

  @Override
  public Permission findById(Long id) {
    return manager.find(Permission.class, id);
  }

  @Transactional
  @Override
  public Permission save(Permission permission) {
    return manager.merge(permission);
  }

  @Transactional
  @Override
  public void remove(Permission permission) {
    permission = findById(permission.getId());
    manager.remove(permission);
  }
}
