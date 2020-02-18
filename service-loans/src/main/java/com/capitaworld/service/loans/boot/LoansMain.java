
package com.capitaworld.service.loans.boot;

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
import com.capitaworld.client.ekyc.EPFClient;
import com.capitaworld.client.eligibility.EligibilityClient;
import com.capitaworld.client.payment.gateway.GatewayClient;
import com.capitaworld.client.reports.ReportsClient;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.itr.client.ITRClient;
import com.capitaworld.service.BodmasClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.auth.client.AuthClient;
import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.fraudanalytics.client.FraudAnalyticsClient;
import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.mca.client.McaClient;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.pennydrop.client.PennydropClient;
import com.capitaworld.service.rating.RatingClient;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.thirdpaty.client.ThirdPartyClient;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.source.provider.util.CommonUtils;

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
	
	@Value("${capitaworld.service.auth.url}")
	String authUrl;

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

	@Value("${capitaworld.service.thirdparty.url}")
	private String thirdPartyBaseUrl;

	@Value("${capitaworld.service.mca.url}")
	private String mcaClientUrl;

	@Value("${capitaworld.service.workflow.url}")
	private String workFlowClientUrl;

	@Value("${capitaworld.service.eligibility.url}")
	private String eligibilityUrl;

	@Value("${capitaworld.service.fraudanalytics.url}")
	private String fraudAnalyticsUrl;

	@Value("${capitaworld.service.itr.url}")
	private String itrUrl;
	
	@Value("${capitaworld.service.gateway.url}")
	private String gatewayUrl;
	
	@Value("${capitaworld.service.eky.url}")
	private String ekycUrl;
	
	@Value("${capitaworld.service.bodmas.url}")
	private String bodmasBaseUrl;
	
	@Value("${capitaworld.service.pennydrop.url}")
	private String pennydropBaseUrl;


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
		MatchEngineClient matchEngineClient = new MatchEngineClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.match_engine_mudra));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(matchEngineClient);
		return matchEngineClient;
	}

	@Bean
	public ProposalDetailsClient proposalDetailsClient() {
		ProposalDetailsClient proposalDetailsClient = new ProposalDetailsClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.match_engine_mudra));
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
		ReportsClient reportsClient = new ReportsClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.reports_mudra));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(reportsClient);
		return reportsClient;
	}

	@Bean
	public ScoringClient scoringClient() {
		ScoringClient scoringClient = new ScoringClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.scoring_mudra));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(scoringClient);
		return scoringClient;
	}

	@Bean
	public GstClient gstClient() {
		GstClient gstClient = new GstClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.gst));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(gstClient);
		return gstClient;
	}

	@Bean
	public AnalyzerClient analyzerClient() {
		AnalyzerClient analyzerClient = new AnalyzerClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.analyzer));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(analyzerClient);
		return analyzerClient;
	}

	@Bean
	public ConnectClient connectClient() {
		ConnectClient connectClient = new ConnectClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.connect_mudra));
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
		EligibilityClient eligibilityClient = new EligibilityClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.eligibility_mudra));
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
		ITRClient itrClient = new ITRClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.itr));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(itrClient);
		return itrClient;
	}
	
	@Bean
	public AuthClient authClient() {
		AuthClient authClient = new AuthClient(CommonUtils.getLocalIpAddress(CommonUtils.UrlType.auth));
		applicationContext.getAutowireCapableBeanFactory().autowireBean(authClient);
		return authClient;
	}
	
	@Bean
	public BodmasClient bodmasClient() {
		BodmasClient bodmasClient = new BodmasClient(bodmasBaseUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(bodmasClient);
		return bodmasClient;		
	}
	
	@Bean
	public PennydropClient pennyDropClient() {
		PennydropClient pennyDropClient = new PennydropClient(pennydropBaseUrl);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(pennyDropClient);
		return pennyDropClient;		
	}

}
