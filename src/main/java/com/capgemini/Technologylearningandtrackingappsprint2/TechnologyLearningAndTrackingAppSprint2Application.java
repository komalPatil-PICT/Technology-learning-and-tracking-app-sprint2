package com.capgemini.Technologylearningandtrackingappsprint2;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.function.Predicate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
@SpringBootApplication(scanBasePackages = "com.capgemini.tlta")
@EntityScan(basePackages = "com.capgemini.tlta.model")
@EnableJpaRepositories(basePackages = "com.capgemini.tlta.repository")
@EnableOpenApi
public class TechnologyLearningAndTrackingAppSprint2Application {

	public static void main(String[] args) {
		SpringApplication.run(TechnologyLearningAndTrackingAppSprint2Application.class, args);
	}

	@Bean
    public Docket openApiEmployeeStore() {
      return new Docket(DocumentationType.OAS_30)
          .groupName("open-api-tlta-management")
          .select()
          .paths(TLTAPaths())
          .build();
    }
    private Predicate<String> TLTAPaths() {
      return regex(".*/api/.*");
    }
}
