package com.capitaworld.service.loans.service.sanctionimpl;

import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.config.FPAsyncComponent;
import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoanSanctionRequest;
import com.capitaworld.service.loans.repository.OfflineProcessedAppRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.sanction.LoanSanctionRepository;
import com.capitaworld.service.loans.service.sanction.LoanSanctionService;
import com.capitaworld.service.loans.utils.CommonUtils;
/**
 * @author Ankit
 *
 */

@Service
@Transactional
public class LoanSanctionServiceImpl implements LoanSanctionService {
	private static final Logger logger = LoggerFactory.getLogger(LoanSanctionServiceImpl.class);

	private static final String SUCCESS_LITERAL = "SUCCESS";

	@Autowired
	private LoanSanctionRepository loanSanctionRepository;
	
	@Autowired 
	private ProposalDetailsRepository proposalDetailsRepository;
	

	
	@Value("${capitaworld.sidbi.integration.is_production}")
	private String isProduction; 
	
	@Value("${capitaworld.sidbi.integration.reverse_api_timeout}")
	private String reverseAPITimeOut; 
	
	@Autowired
	private FPAsyncComponent fpAsyncComponent;
	
	@Autowired
	private OfflineProcessedAppRepository offlineProcessedAppRepository;

	@Override
	public Boolean saveLoanSanctionDetail(LoanSanctionRequest loanSanctionRequest) throws LoansException {
		try {
		logger.info("Enter in saveLoanSanctionDetail() ----------------------->  LoanSanctionRequest==> "+ loanSanctionRequest);
		
		LoanSanctionDomain loanSanctionDomainOld =loanSanctionRepository.findByAppliationId(loanSanctionRequest.getApplicationId());
		if(CommonUtils.isObjectNullOrEmpty(loanSanctionDomainOld) ) {
			loanSanctionDomainOld = new LoanSanctionDomain();
			BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomainOld,"id");
			loanSanctionDomainOld.setOrgId(!CommonUtils.isObjectNullOrEmpty(loanSanctionRequest.getOrgId()) ? loanSanctionRequest.getOrgId() : null);
			if(loanSanctionRequest.getIsIneligibleProposal() != null && loanSanctionRequest.getIsIneligibleProposal() == true) {
				loanSanctionDomainOld.setIsSanctionedFrom(loanSanctionRequest.getIsSanctionedFrom());
				/*IneligibleProposalDetails ineligibleProposalDetails = (IneligibleProposalDetails) offlineProcessedAppRepository.findByAppliationId(loanSanctionRequest.getApplicationId());
				ineligibleProposalDetails.setIsSanctioned(true);*/
				//update sanctioned is true flag in ineligible proposal table
				Long userId = null;
				if(!CommonUtils.isObjectNullOrEmpty(loanSanctionRequest.getActionBy())) {
					userId = Long.valueOf(loanSanctionRequest.getActionBy());
				}
				offlineProcessedAppRepository.updateSanctionedFlag(loanSanctionRequest.getApplicationId(), loanSanctionRequest.getOrgId(), loanSanctionRequest.getBranch(), userId);
			} else if(CommonUtils.isObjectNullOrEmpty(loanSanctionRequest.getIsIneligibleProposal()) || loanSanctionRequest.getIsIneligibleProposal() == false) {
				loanSanctionDomainOld.setIsSanctionedFrom(CommonUtils.sanctionedFrom.ELIGIBLE_USERS);
			}
			loanSanctionDomainOld.setCreatedBy(loanSanctionRequest.getActionBy());
			loanSanctionDomainOld.setCreatedDate(new Date());
			loanSanctionDomainOld.setIsActive(true);
		}else{
			//BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomainOld,"id");
			if(loanSanctionRequest.getIsIneligibleProposal() != null && loanSanctionRequest.getIsIneligibleProposal()) {
				loanSanctionDomainOld.setIsSanctionedFrom(loanSanctionRequest.getIsSanctionedFrom());
				/*IneligibleProposalDetails ineligibleProposalDetails = (IneligibleProposalDetails) offlineProcessedAppRepository.findByAppliationId(loanSanctionRequest.getApplicationId());
				ineligibleProposalDetails.setIsSanctioned(true);*/
				//update sanctioned is true flag in ineligible proposal table
				Long userId = null;
				if(!CommonUtils.isObjectNullOrEmpty(loanSanctionRequest.getActionBy())) {
					userId = Long.valueOf(loanSanctionRequest.getActionBy());
				}
				offlineProcessedAppRepository.updateSanctionedFlag(loanSanctionRequest.getApplicationId(), loanSanctionRequest.getOrgId(), loanSanctionRequest.getBranch(), userId);
			} else {
				loanSanctionDomainOld.setIsSanctionedFrom(CommonUtils.sanctionedFrom.ELIGIBLE_USERS);
			}
			loanSanctionDomainOld.setSanctionAmount(loanSanctionRequest.getSanctionAmount());
			loanSanctionDomainOld.setSanctionDate(new Date());
			loanSanctionDomainOld.setTenure(loanSanctionRequest.getTenure());
			loanSanctionDomainOld.setRoi(loanSanctionRequest.getRoi());
			loanSanctionDomainOld.setProcessingFee(loanSanctionRequest.getProcessingFee());
			loanSanctionDomainOld.setRemark(loanSanctionRequest.getRemark());
			loanSanctionDomainOld.setModifiedBy(loanSanctionRequest.getActionBy());
			loanSanctionDomainOld.setModifiedDate(new Date());
			/*loanSanctionDomainOld.setIsSanctionedFrom(1l);*/
		}
		//==================Sending Mail notification to Maker=============================
		try{
			fpAsyncComponent.sendEmailToFSWhenCheckerSanctionLoan(loanSanctionDomainOld);
			fpAsyncComponent.sendEmailToMakerHOBOWhenCheckerSanctionLoan(loanSanctionDomainOld);			
		}catch(Exception e){
			logger.error("Exception : {}",e);
		}
		//=================================================================================
		//logger.info("Exit saveLoanSanctionDetail() -----------------------> LoanSanctionDomain "+ loanSanctionDomainOld);
		return loanSanctionRepository.save(loanSanctionDomainOld) != null;
		}catch (Exception e) {
			logger.error("Error/Exception in saveLoanSanctionDetail() -----------------------> Message : ",e);
			throw e;
		}

	}
	
	@Override
	public String sanctionRequestValidation( Long applicationId,Long orgId) throws LoansException {
		logger.info("Enter in requestValidation() ----------------------->  applicationId==> "+ applicationId);
	        try {        	
		 
	        	Long recCount = proposalDetailsRepository.getApplicationIdCountByOrgId(applicationId ,orgId);
	        	if(recCount != null && recCount  > 0) {
	        		return  SUCCESS_LITERAL;
	        	}else {
	        		return "Invalid ApplicationId ";
	        	}		 
	        }catch (Exception e) {
	        	logger.error("Error/Exception in requestValidation() ----------------------->  Message : ", e);
	        	throw e;
			}
	}

	@Override
	public Integer saveSanctionDetailFromPopup(LoanSanctionRequest loanSanctionRequest) throws LoansException {
		logger.info("Enter in saveSanctionDetailFromPopup() ----------------------------- sanctionRequest Data : "+ loanSanctionRequest.toString());
		try {
			
			if(loanSanctionRequest.getIsSanctionedFrom() == 2){
				//FIRST CHECK IF CURRENT PROPOSAL IS ELIGIBL FOR SANCTIONED OR NOT 
				Integer status = offlineProcessedAppRepository.checkBeforeOfflineSanctioned(loanSanctionRequest.getApplicationId());
				if(status == 4) {//OFFLINE
					loanSanctionRequest.setSanctionDate(new Date());
					Boolean result = saveLoanSanctionDetail(loanSanctionRequest);
					return !result ? 0 : 4;
				} else {
					return status;	
				}	
			} else {//OFFLINE
				loanSanctionRequest.setSanctionDate(loanSanctionRequest.getSanctionDate() != null ? loanSanctionRequest.getSanctionDate() :new Date());
				Boolean result = saveLoanSanctionDetail(loanSanctionRequest);
				return !result ? 0 : 4;
			}
			/*loanSanctionRequest.setSanctionDate(new Date());
			return saveLoanSanctionDetail(loanSanctionRequest);*/
			/*logger.info("going to fetch username/password");
			UserOrganisationRequest userOrganisationRequest = userClient.getByOrgId(loanSanctionRequest.getOrgId());
			if(CommonUtils.isObjectListNull( userOrganisationRequest, userOrganisationRequest.getUsername(),  userOrganisationRequest.getPassword() )){
				logger.warn("username/password found null ");
				return false;
			}
			loanSanctionRequest.setUserName(userOrganisationRequest.getUsername());
			loanSanctionRequest.setPassword(userOrganisationRequest.getPassword());*/
		} catch (Exception e) {
			logger.error("Error/Exception in saveSanctionDetailFromPopup() ----------------------->  Message : ",e);
			return 0;
		}
	}
	
}
