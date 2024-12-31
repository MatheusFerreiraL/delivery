package com.github.matheusferreiral.algafoodapi.jpa;

import com.github.matheusferreiral.algafoodapi.AlgafoodApiApplication;
import com.github.matheusferreiral.algafoodapi.domain.model.Kitchen;
import com.github.matheusferreiral.algafoodapi.infrastructure.repository.KitchenRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class AddKitchenMain {
  public static void main(String[] args) {
    ApplicationContext applicationContext =
        new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);
    KitchenRepositoryImpl kitchenRepositoryImpl =
        applicationContext.getBean(KitchenRepositoryImpl.class);

    Kitchen kitchen1 = new Kitchen();
    kitchen1.setName("Indian");

    Kitchen kitchen2 = new Kitchen();
    kitchen2.setName("Italian");

    kitchen1 = kitchenRepositoryImpl.save(kitchen1);
    kitchen2 = kitchenRepositoryImpl.save(kitchen2);

    System.out.printf("%d - %s\n", kitchen1.getId(), kitchen1.getName());
    System.out.printf("%d - %s\n", kitchen2.getId(), kitchen2.getName());
  }
}
