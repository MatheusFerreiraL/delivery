package com.github.matheusferreiral.algafoodapi.jpa;

import com.github.matheusferreiral.algafoodapi.AlgafoodApiApplication;
import com.github.matheusferreiral.algafoodapi.domain.model.Kitchen;
import com.github.matheusferreiral.algafoodapi.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class FindKitchenMain {
  public static void main(String[] args) {
    ApplicationContext applicationContext =
        new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);
    KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);

    Kitchen kitchen = kitchenRepository.findById(1L);

    System.out.printf("%d - %s\n", kitchen.getId(), kitchen.getName());
  }
}
