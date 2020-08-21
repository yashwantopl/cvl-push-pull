
package com.opl.service.loans.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.capitaworld.client.ekyc.EPFClient;
import com.capitaworld.service.pennydrop.client.PennydropClient;
import com.opl.commons.lib.common.CommonUtils;
import com.opl.mudra.client.analyzer.AnalyzerClient;
import com.opl.mudra.client.auth.AuthClient;
import com.opl.mudra.client.cibil.CIBILClient;
import com.opl.mudra.client.connect.ConnectClient;
import com.opl.mudra.client.dms.DMSClient;
import com.opl.mudra.client.eligibility.EligibilityClient;
import com.opl.mudra.client.fraudanalytics.FraudAnalyticsClient;
import com.opl.mudra.client.gst.GstClient;
import com.opl.mudra.client.itr.ITRClient;
import com.opl.mudra.client.matchengine.MatchEngineClient;
import com.opl.mudra.client.matchengine.ProposalDetailsClient;
import com.opl.mudra.client.mca.McaClient;
import com.opl.mudra.client.notification.NotificationClient;
import com.opl.mudra.client.oneform.OneFormClient;
import com.opl.mudra.client.payment.GatewayClient;
import com.opl.mudra.client.rating.RatingClient;
import com.opl.mudra.client.reports.ReportsClient;
import com.opl.mudra.client.scoring.ScoringClient;
import com.opl.mudra.client.thirdparty.ThirdPartyClient;
import com.opl.mudra.client.users.UsersClient;
import com.opl.mudra.client.workflow.WorkflowClient;
import com.opl.profile.client.ProfileClient;


@SpringBootApplication
@ComponentScan(basePackages = { "com.opl" })
@EnableAsync
@EnableScheduling
public class LoansMain {

	@Autowired
	ApplicationContext applicationContext;
	
	@Value("${capitaworld.service.connect.url}")
	private String connectUrl;
	
	@Value("${capitaworld.service.auth.url}")
	private String authUrl;
	
	@Value("${capitaworld.service.profile.url}")
	private String profileUrl;
	
	@Value("${capitaworld.service.itr.url}")
	private String itrUrl;
	
	@Value("${capitaworld.service.gst.url}")
	private String gstUrl;
	
	
	@Value("${capitaworld.service.analyzer.url}")
	private String analyzerUrl;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(LoansMain.class, args);
	}

	@Bean
	public GatewayClient gatewayClient() {
		GatewayClient gatewayClient = new GatewayClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.gateway));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(gatewayClient);
		return gatewayClient;
	}
	
	@Bean
	public EPFClient epfClient() {
		EPFClient epfClient = new EPFClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.ekyc));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(epfClient);
		return epfClient;
	}
	
	@Bean
	public DMSClient dmsMasterClient() {
		DMSClient dmsClient = new DMSClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.dms));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(dmsClient);
		return dmsClient;
	}

	@Bean
	public NotificationClient notificationMasterClient() {
		NotificationClient notificationClient = new NotificationClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.notification));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(notificationClient);
		return notificationClient;
	}

	@Bean
	public UsersClient userMasterClient() {
		UsersClient usersClient = new UsersClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.users));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(usersClient);
		return usersClient;
	}

	@Bean
	public OneFormClient oneFormClient() {
		OneFormClient oneFormClient = new OneFormClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.oneform));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(oneFormClient);
		return oneFormClient;
	}

	@Bean
	public MatchEngineClient matchEngineClient() {
		MatchEngineClient matchEngineClient = new MatchEngineClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.matchengine));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(matchEngineClient);
		return matchEngineClient;
	}

	@Bean
	public ProposalDetailsClient proposalDetailsClient() {
		ProposalDetailsClient proposalDetailsClient = new ProposalDetailsClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.matchengine));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(proposalDetailsClient);
		return proposalDetailsClient;
	}

	@Bean
	public CIBILClient cibilClient() {
		CIBILClient cibilClient = new CIBILClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.cibil));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(cibilClient);
		return cibilClient;
	}

	@Bean
	public RatingClient ratingClient() {
		RatingClient ratingClient = new RatingClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.rating));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(ratingClient);
		return ratingClient;
	}

	@Bean
	public ReportsClient reportsClient() {
		ReportsClient reportsClient = new ReportsClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.reports));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(reportsClient);
		return reportsClient;
	}

	@Bean
	public ScoringClient scoringClient() {
		ScoringClient scoringClient = new ScoringClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.scoring));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(scoringClient);
		return scoringClient;
	}

	@Bean
	public GstClient gstClient() {
		GstClient gstClient = new GstClient(gstUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(gstClient);
		return gstClient;
	}

	@Bean
	public AnalyzerClient analyzerClient() {
		AnalyzerClient analyzerClient = new AnalyzerClient(analyzerUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(analyzerClient);
		return analyzerClient;
	}

	@Bean
	public ConnectClient connectClient() {
		ConnectClient connectClient = new ConnectClient(connectUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(connectClient);
		return connectClient;
	}

	@Bean
	public ThirdPartyClient thirdPartyClient() {
		ThirdPartyClient thirdPartyClient = new ThirdPartyClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.thirdparty));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(thirdPartyClient);
		return thirdPartyClient;
	}

	@Bean
	public McaClient mcaClient() {
		McaClient mcaClient = new McaClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.mca));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(mcaClient);
		return mcaClient;
	}

	@Bean
	public WorkflowClient workFlowClient() {
		WorkflowClient workflowClient = new WorkflowClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.workflow));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(workflowClient);
		return workflowClient;
	}

	@Bean
	public EligibilityClient eligibilityClient() {
		EligibilityClient eligibilityClient = new EligibilityClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.eligibility));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(eligibilityClient);
		return eligibilityClient;
	}

	@Bean
	public FraudAnalyticsClient fraudAnalyticsClient() {
		FraudAnalyticsClient fraudAnalyticsClient = new FraudAnalyticsClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.fraudanalytics));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(fraudAnalyticsClient);
		return fraudAnalyticsClient;
	}

	@Bean
	public ITRClient itrClient() {
		ITRClient itrClient = new ITRClient(itrUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(itrClient);
		return itrClient;
	}
	
	@Bean
	public AuthClient authClient() {
		AuthClient authClient = new AuthClient(authUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(authClient);
		return authClient;
	}
	
	@Bean
	public PennydropClient pennyDropClient() {
		PennydropClient pennyDropClient = new PennydropClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.pennydrop));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(pennyDropClient);
		return pennyDropClient;		
	}

	@Bean
	public ProfileClient profileClient() {
		ProfileClient profileClient = new ProfileClient(profileUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(profileClient);
		return profileClient;
	}


}
