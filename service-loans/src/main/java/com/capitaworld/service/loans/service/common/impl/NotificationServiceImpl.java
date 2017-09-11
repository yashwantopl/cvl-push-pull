package com.capitaworld.service.loans.service.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantRequest;
import com.capitaworld.service.loans.service.common.NotificationService;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.matchengine.utils.MatchConstant;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.notification.exceptions.NotificationException;
import com.capitaworld.service.notification.model.Notification;
import com.capitaworld.service.notification.model.NotificationRequest;
import com.capitaworld.service.notification.utils.ContentType;
import com.capitaworld.service.notification.utils.NotificationType;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService{
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
	
	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@Autowired
	private ProductMasterService productMasterService;
	
	@Autowired
	private NotificationClient notificationClient;
	
	@Autowired
	private CorporateApplicantService corporateApplicantService;
	
	@Autowired
	private RetailApplicantService retailApplicantService;
	
	private static Notification createNotification(String[] toIds, Long fromId, Long fromUserTypeId, Long templateId,
			Map<String, Object> parameters, Long applicationId, Long fpProductId) {
        CommonDocumentUtils.startHook(logger, "createNotification");
        
 		Notification notification = new Notification();
		notification.setTo(toIds);
		notification.setType(NotificationType.SYSTEM);
		notification.setTemplateId(templateId);
		notification.setContentType(ContentType.TEMPLATE);
		notification.setParameters(parameters);
		notification.setFrom(fromId.toString());
		notification.setProductId(fpProductId);
		notification.setApplicationId(applicationId);
		CommonDocumentUtils.endHook(logger, "createNotification");
		return notification;

	}

	@Override
	public void sendViewNotification(String toUserId, Long fromUserId, Long fromUserTypeId, Long notificationId,
			Long applicationId, Long fpProductId) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "sendViewNotification");
		
		if (toUserId != null && fromUserId != null) {
			String[] a = { toUserId.toString() };
			NotificationRequest request = new NotificationRequest();
			request.setClientRefId(fromUserId.toString());
			Map<String, Object> parameters = new HashMap<String, Object>();
				try {
					int fsProdId;
					if(CommonUtils.UserType.FUND_SEEKER == fromUserTypeId)
						fsProdId =loanApplicationService.getProductIdByApplicationId(applicationId, fromUserId);
					else
						fsProdId =loanApplicationService.getProductIdByApplicationId(applicationId, Long.parseLong(toUserId));
					
					int fsType = CommonUtils.getUserMainType(fsProdId);
					if(CommonUtils.UserMainType.CORPORATE == fsType){
						CorporateApplicantRequest corporateApplicantRequest;
						if(CommonUtils.UserType.FUND_SEEKER == fromUserTypeId)
							corporateApplicantRequest = corporateApplicantService.getCorporateApplicant(fromUserId, applicationId);
						else
							corporateApplicantRequest = corporateApplicantService.getCorporateApplicant(Long.parseLong(toUserId), applicationId);
						parameters.put("fs_name",corporateApplicantRequest.getOrganisationName());
					}else if(CommonUtils.UserMainType.RETAIL == fsType){
						RetailApplicantRequest retailApplicantRequest;
						if(CommonUtils.UserType.FUND_SEEKER == fromUserTypeId)
							retailApplicantRequest = retailApplicantService.get(fromUserId, applicationId);
						else
							retailApplicantRequest = retailApplicantService.get(Long.parseLong(toUserId), applicationId);
						
						parameters.put("fs_name",(!CommonUtils.isObjectNullOrEmpty(retailApplicantRequest.getFirstName()) ? retailApplicantRequest.getFirstName() : "") + " " + (!CommonUtils.isObjectNullOrEmpty(retailApplicantRequest.getLastName()) ? retailApplicantRequest.getLastName() : ""));
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					parameters.put("fs_name", "NA");
				}
				Object o[]=productMasterService.getUserDetailsByPrductId(fpProductId);
				try {
					if(o!=null)
						parameters.put("fp_name",o[1].toString());
					else
						parameters.put("fp_name","NA");
					

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace(); 
					parameters.put("fp_name", "NA");
				}
				try {
					if(o!=null)
						parameters.put("fp_pname", o[2].toString());
					else
						parameters.put("fp_pname", "NA");
				} catch (Exception e) {
					// TODO: handle exception
					parameters.put("fp_pname", "NA");
				}
				request.addNotification(createNotification(a, fromUserId, fromUserTypeId,notificationId, parameters, applicationId, fpProductId));
			try {
				notificationClient.send(request);
			} catch (NotificationException e) {
				e.printStackTrace();
			}
			CommonDocumentUtils.endHook(logger, "sendViewNotification");
		}
		
	}

}
