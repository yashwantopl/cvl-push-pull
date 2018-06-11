package com.capitaworld.service.loans.boot;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.capitaworld.cibil.client.CIBILClient;
import com.capitaworld.client.reports.ReportsClient;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.gateway.client.GatewayClient;
import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.rating.RatingClient;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.thirdpaty.client.ThirdPartyClient;
//import com.capitaworld.service.rating.RatingClient;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.sidbi.integration.client.SidbiIntegrationClient;

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

	@Value("${capitaworld.service.scoring.url}")
	private String scoringBaseUrl;

	@Value("${capitaworld.service.gst.url}")
	private String gstBaseUrl;

	@Value("${capitaworld.service.analyzer.url}")
	private String analyzerBaseUrl;
	
	@Value("${capitaworld.service.connect.url}")
	private String connectBaseUrl;
	
	@Value("${capitaworld.service.sidbi.integration.url}")
	private String sidbiIntegrationBaseUrl;

	@Value("${capitaworld.sidbi.integration.username}")
	private String sidbiUserName;

	@Value("${capitaworld.sidbi.integration.password}")
	private String sidbiPassword;
	
	@Value("${capitaworld.service.thirdparty.url}")
	private String thirdPartyBaseUrl;
	
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


	@Bean
	public ScoringClient scoringClient() {
		ScoringClient scoringClient = new ScoringClient(scoringBaseUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(scoringClient);
		return scoringClient;
	}

	@Bean
	public GstClient gstClient() {
		GstClient gstClient = new GstClient(gstBaseUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(gstClient);
		return gstClient;
	}

	@Bean
	public AnalyzerClient analyzerClient() {
		AnalyzerClient analyzerClient = new AnalyzerClient(analyzerBaseUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(analyzerClient);
		return analyzerClient;
	}
	
	@Bean
	public ConnectClient connectClient() {
		ConnectClient connectClient = new ConnectClient(connectBaseUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(connectClient);
		return connectClient;
	}
	
	@Bean
	public ThirdPartyClient thirdPartyClient() {
		ThirdPartyClient thirdPartyClient = new ThirdPartyClient(thirdPartyBaseUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(thirdPartyClient);
		return thirdPartyClient;
	}
	
	@Bean
	public SidbiIntegrationClient sidbiIntegrationClient() {
		String keyToEncode = sidbiUserName + ":" + sidbiPassword;
		System.out.println("keyToEncode UPdated===============>" + keyToEncode);
		String encodedString = "Basic " + Base64.getEncoder().encodeToString(keyToEncode.getBytes());
		System.out.println("encodedString UPdated===============>" + encodedString);
		SidbiIntegrationClient sidbiIntegrationClient = new SidbiIntegrationClient(sidbiIntegrationBaseUrl,encodedString);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(sidbiIntegrationClient);
		return sidbiIntegrationClient;
	}

}
