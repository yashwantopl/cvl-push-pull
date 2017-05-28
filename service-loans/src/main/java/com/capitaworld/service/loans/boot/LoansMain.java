package com.capitaworld.service.loans.boot;

import com.capitaworld.service.dms.client.DMSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author win7
 *
 */
/*@SpringBootApplication
@ComponentScan(basePackages = {"com.capitaworld"})
public class LoansMain {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(LoansMain.class, args);
	}

}*/
@SpringBootApplication
@ComponentScan(basePackages = { "com.capitaworld" })
@EnableAsync
public class LoansMain {

	@Autowired
	ApplicationContext applicationContext;

	@Value("${dmsURL}")
	String dmsUrl;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(LoansMain.class, args);
	}

	@Bean
	public DMSClient dmsMasterClient() {
		DMSClient dmsClient = new DMSClient(dmsUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(dmsClient);
		return dmsClient;
	}

}
