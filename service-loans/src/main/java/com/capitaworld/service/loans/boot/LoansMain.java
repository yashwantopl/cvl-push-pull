package com.capitaworld.service.loans.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.capitaworld" })
public class LoansMain {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(LoansMain.class, args);
	}

}
