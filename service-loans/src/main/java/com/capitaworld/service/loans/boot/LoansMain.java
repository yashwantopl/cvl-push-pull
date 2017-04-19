package com.capitaworld.service.loans.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.capitaworld.service" })
@EnableJpaRepositories("com.capitaworld.service.loans.repository")
@EntityScan("com.capitaworld.service.loans.domain")
public class LoansMain {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(LoansMain.class, args);
	}

}
