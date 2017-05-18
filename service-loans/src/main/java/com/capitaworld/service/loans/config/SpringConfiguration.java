package com.capitaworld.service.loans.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SpringConfiguration extends WebMvcConfigurerAdapter {
	
	
	@Bean
	AuthenticationInterceptor authenticationInterceptor(){
		return new AuthenticationInterceptor();
	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**");
	}
	
}
