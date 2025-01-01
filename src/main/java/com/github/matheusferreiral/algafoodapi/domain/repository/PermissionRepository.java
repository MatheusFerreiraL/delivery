package com.github.matheusferreiral.algafoodapi.domain.repository;

import com.github.matheusferreiral.algafoodapi.domain.model.Permission;
import java.util.List;

public interface PermissionRepository {

  List<Permission> list();

  Permission findById(Long id);

  Permission save(Permission permission);

  void remove(Permission permission);
}
