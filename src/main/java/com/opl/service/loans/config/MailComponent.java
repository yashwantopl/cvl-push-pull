package com.opl.service.loans.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opl.mudra.api.common.CommonUtils;
import com.opl.mudra.api.notification.exception.NotificationException;
import com.opl.mudra.api.notification.model.ContentAttachment;
import com.opl.mudra.api.notification.model.Notification;
import com.opl.mudra.api.notification.model.NotificationRequest;
import com.opl.mudra.api.notification.model.NotificationResponse;
import com.opl.mudra.api.notification.utils.ContentType;
import com.opl.mudra.api.notification.utils.NotificationType;
import com.opl.mudra.client.connect.ConnectClient;
import com.opl.mudra.client.notification.NotificationClient;
import com.opl.mudra.client.payment.GatewayClient;
import com.opl.mudra.client.users.UsersClient;
import com.opl.service.loans.repository.common.CommonRepository;
import com.opl.service.loans.repository.fundprovider.ProductMasterRepository;
import com.opl.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.opl.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.opl.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.opl.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.opl.service.loans.service.ProposalService;

@Component
public class MailComponent {

	private static final Logger logger = LoggerFactory.getLogger(MailComponent.class.getName());

	@Autowired
	LoanApplicationRepository loanApplicationMasterRepository;
	
	@Autowired
	ProposalDetailsRepository proposalDetailsRepository;
	
	@Autowired
	ConnectClient connectClient;
	
	@Autowired
	CommonRepository commonRepository;
	
	@Autowired
	ApplicationProposalMappingRepository applicationProposalMappingRepository;
	
	@Autowired
	CorporateApplicantDetailRepository corporateApplicantDetailRepository;
	
	@Autowired
	ProductMasterRepository fpProductMasterRep;
	
	@Autowired
	UsersClient userClient;
	
	@Autowired
	AsyncComponent asyncComp;
	
	@Autowired
	GatewayClient gatewayClient;
	
	@Autowired
	NotificationClient notificationClient;
	
	@Autowired
	ProposalService proposalService;
	
	
	public Boolean sendMailWithAttachment(String toNo, String[] cc ,  Map<String,Object> mailParamter , List<ContentAttachment> fileList ,Long userId , 
			Long emailTemplateAliasId , Object subject  ,String[] bcc, Long domainId ,Long orgId , Integer loanTypeId ,Long masterId) throws NotificationException {
		
		logger.info("Inside send Email===>{}",toNo);
		Notification notification = new Notification();
		String[] to= {toNo} ; 
		notification.setTo(to);
		String userId1 = userId != null ? userId.toString() : "1";
			
		if(cc !=null && cc.length > 0 ) {
			notification.setCc(cc);
		}
		if(bcc !=null && bcc.length > 0 ) {
			notification.setBcc(bcc);
		}
		notification.setTemplateId(emailTemplateAliasId);
		notification.setType(NotificationType.EMAIL);
		notification.setContentType(ContentType.TEMPLATE);
//        notification.setFrom(environment.getRequiredProperty("com.capitaworld.mail.url"));
		notification.setSubject(subject);  
//		notification.setContent("ALIAS");
		
		//attach file in mail
		if(fileList != null && !CommonUtils.isObjectListNull(fileList)) {
			notification.setContentAttachments(fileList);
		}
		
		//set mail paramters
		notification.setParameters(mailParamter);
		
		notification.setLoanTypeId(loanTypeId);
		notification.setUserOrgId(orgId);
		notification.setMasterId(masterId);
		
		NotificationRequest notificationRequest = new NotificationRequest();
		notificationRequest.setClientRefId(userId1);
		notificationRequest.setDomainId(domainId);
		notificationRequest.addNotification(notification);
		
		/**Boolean sendEmail = sendEmail(notificationRequest);*/
		return sendEmail(notificationRequest);
	}
	
	
	private Boolean sendEmail(NotificationRequest notificationRequest) throws NotificationException {
		boolean status=false;
		logger.info("Inside send Email===>{}",notificationRequest.getNotifications().get(0).getTemplateId());
		NotificationResponse notificationResponse = notificationClient.send(notificationRequest);
		logger.info("Outside send Email===>{}",notificationRequest.getNotifications().get(0).getTemplateId());
		if(notificationResponse.getStatus() != null && notificationResponse.getStatus()== 200){
			status=true;
		}
		return status;
	}	
	
	//use to send SMS to all types
		public void sendSMSNotification(String toNo , String userId, Map<String, Object> parameters, Long templateId , Long domainId, Long orgId , 
				Integer loanTypeId ,Long masterId) throws NotificationException {
			logger.info("Inside send SMS===>{}", toNo);
			String[] to= {toNo } ; 
			NotificationRequest req = new NotificationRequest();
			req.setClientRefId(userId);
			req.setDomainId(domainId);
			Notification notification = new Notification();
			notification.setContentType(ContentType.TEMPLATE);
			notification.setTemplateId(templateId);
			notification.setTo(to);
			notification.setType(NotificationType.SMS);
			notification.setParameters(parameters);
			
			notification.setLoanTypeId(loanTypeId);
			notification.setUserOrgId(orgId);
			notification.setMasterId(masterId);
			req.addNotification(notification);

			sendEmail(req);
			logger.info("Outside send SMS===>{}",toNo);

		}
		

		//use to send SYS to all types
		public void sendSYSNotification(String toNo , Long applicationId, String toUserId, Map<String, Object> parameters,
				Long templateId, String fromId , Long domainId ,Long orgId , Integer loanTypeId ,Long masterId) throws NotificationException {
			logger.info("Inside send SYSTEM notification===>{}",toNo);
			String[] to= {toNo } ; 
			NotificationRequest req = new NotificationRequest();
			req.setClientRefId(toUserId);
			req.setDomainId(domainId);
			Notification notification = new Notification();
			notification.setContentType(ContentType.TEMPLATE);
			notification.setTemplateId(templateId);
			notification.setTo(to);
			notification.setType(NotificationType.SYSTEM);
			notification.setParameters(parameters);
			notification.setFrom(fromId);
			notification.setApplicationId(applicationId);
			
			notification.setLoanTypeId(loanTypeId);
			notification.setUserOrgId(orgId);
			notification.setMasterId(masterId);
			req.addNotification(notification);

			sendEmail(req);
			logger.info("Outside send SYSTEM notification===>{}" ,toNo);

		}

	
}
