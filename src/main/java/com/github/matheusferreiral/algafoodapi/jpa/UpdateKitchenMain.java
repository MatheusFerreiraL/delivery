package com.github.matheusferreiral.algafoodapi.jpa;

import com.github.matheusferreiral.algafoodapi.AlgafoodApiApplication;
import com.github.matheusferreiral.algafoodapi.domain.model.Kitchen;
import com.github.matheusferreiral.algafoodapi.infrastructure.repository.KitchenRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class UpdateKitchenMain {
  public static void main(String[] args) {
    ApplicationContext applicationContext =
        new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);
    KitchenRepositoryImpl kitchenRepositoryImpl =
        applicationContext.getBean(KitchenRepositoryImpl.class);

    Kitchen kitchen = new Kitchen();
    kitchen.setId(1L);
    kitchen.setName("Arabic");

    kitchenRepositoryImpl.save(kitchen);
  }
}
