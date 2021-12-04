package com.raman.crudemployee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaRepositories(value = "com.raman.crudemployee.repository")
@OpenAPIDefinition(info = @Info(title="Employee CRDU API with Angular", version="1.0", description ="Emolyee Services"))
public class CrudAngularRestApiEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudAngularRestApiEmployeeApplication.class, args);
	}

}
