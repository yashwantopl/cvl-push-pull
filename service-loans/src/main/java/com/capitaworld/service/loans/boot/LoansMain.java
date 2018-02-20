package com.capitaworld.service.loans.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.capitaworld.cibil.client.CIBILClient;
import com.capitaworld.client.reports.ReportsClient;
import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.gateway.client.GatewayClient;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.rating.RatingClient;
//import com.capitaworld.service.rating.RatingClient;
import com.capitaworld.service.users.client.UsersClient;

/**
 * @author win7
 *
 */
/*
 * @SpringBootApplication
 * 
 * @ComponentScan(basePackages = {"com.capitaworld"}) public class LoansMain {
 * 
 * public static void main(String[] args) throws Exception {
 * SpringApplication.run(LoansMain.class, args); }
 * 
 * }
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.capitaworld" })
@EnableAsync
@EnableScheduling
public class LoansMain {

	@Autowired
	ApplicationContext applicationContext;

	@Value("${dmsURL}")
	String dmsUrl;

	@Value("${notificationURL}")
	String notificationURL;

	@Value("${userURL}")
	String userURL;

	@Value("${oneForm}")
	private String oneFormUrl;

	@Value("${matchesURL}")
	private String matchEngineUrl;

	@Value("${ratingURL}")
	private String ratingUrl;

	@Value("${capitaworld.service.cibil.url}")
	private String cibilUrl;

	@Value("${capitaworld.service.gateway.url}")
	private String gatewayBaseUrl;
	
	@Value("${capitaworld.service.reports.url}")
	private String reportsBaseUrl;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(LoansMain.class, args);
	}

	@Bean
	public DMSClient dmsMasterClient() {
		DMSClient dmsClient = new DMSClient(dmsUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(dmsClient);
		return dmsClient;
	}

	@Bean
	public NotificationClient notificationMasterClient() {
		NotificationClient notificationClient = new NotificationClient(notificationURL);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(notificationClient);
		return notificationClient;
	}

	@Bean
	public UsersClient userMasterClient() {
		UsersClient usersClient = new UsersClient(userURL);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(usersClient);
		return usersClient;
	}

	@Bean
	public OneFormClient oneFormClient() {
		OneFormClient oneFormClient = new OneFormClient(oneFormUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(oneFormClient);
		return oneFormClient;
	}

	@Bean
	public MatchEngineClient matchEngineClient() {
		MatchEngineClient matchEngineClient = new MatchEngineClient(matchEngineUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(matchEngineClient);
		return matchEngineClient;
	}

	@Bean
	public ProposalDetailsClient proposalDetailsClient() {
		ProposalDetailsClient proposalDetailsClient = new ProposalDetailsClient(matchEngineUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(proposalDetailsClient);
		return proposalDetailsClient;
	}

	@Bean
	public CIBILClient cibilClient() {
		CIBILClient cibilClient = new CIBILClient(cibilUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(cibilClient);
		return cibilClient;
	}

	@Bean
	public RatingClient ratingClient() {
		RatingClient ratingClient = new RatingClient(ratingUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(ratingClient);
		return ratingClient;
	}

	@Bean
	public GatewayClient gatewayClient() {
		GatewayClient gatewayClient = new GatewayClient(gatewayBaseUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(gatewayClient);
		return gatewayClient;
	}
	
	@Bean
	public ReportsClient reportsClient() {
		ReportsClient reportsClient = new ReportsClient(reportsBaseUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(reportsClient);
		return reportsClient;
	}

}
