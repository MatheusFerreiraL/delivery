package com.github.matheusferreiral.algafoodapi.jpa;

import com.github.matheusferreiral.algafoodapi.AlgafoodApiApplication;
import com.github.matheusferreiral.algafoodapi.domain.model.Kitchen;
import com.github.matheusferreiral.algafoodapi.infrastructure.repository.KitchenRepositoryImpl;
import java.util.List;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class QueryKitchenMain {
  public static void main(String[] args) {
    ApplicationContext applicationContext =
        new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);
    KitchenRepositoryImpl kitchenRepositoryImpl =
        applicationContext.getBean(KitchenRepositoryImpl.class);

    List<Kitchen> kitchens = kitchenRepositoryImpl.list();

    for (Kitchen kitchen : kitchens) {
      System.out.println(kitchen.getName());
    }
  }
}
