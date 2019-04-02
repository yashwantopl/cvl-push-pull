package com.capitaworld.service.loans.service.common.impl;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.DashboardProfileResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.common.DashboardService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService {

	private static final Logger logger = LoggerFactory.getLogger(DashboardServiceImpl.class);

	private static final String GET_BASIC_PROFILE_INFO = "getBasicProfileInfo";

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private ApplicationProposalMappingRepository applicationProposalMappingRepository;

	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;

	@Autowired
	private OneFormClient  oneFormClient;
	
	@Autowired
	private UsersClient usersClient;

	@Autowired
	private PrimaryCorporateDetailRepository primaryCorporateDetailRepository;

	@Override
	public DashboardProfileResponse getBasicProfileInfo(Long applicationId, Long userId,boolean isSP) throws LoansException {
		CommonDocumentUtils.startHook(logger, GET_BASIC_PROFILE_INFO);

		Integer productId = null;
		/*if(isSP){
			productId = loanApplicationRepository.getProductIdByApplicationIdForSP(applicationId, userId);
		}else{
			productId = loanApplicationRepository.getProductIdByApplicationId(applicationId, userId);			
		}*/
		ApplicationProposalMapping loanApplicationMaster = applicationProposalMappingRepository.getLastByApplicationIdAndUserId(applicationId,userId);
		int userMainType = 0;CommonUtils.getUserMainType(productId);
		if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getProductId())){
			productId = loanApplicationMaster.getProductId();
			userMainType = CommonUtils.getUserMainType(productId);
		}else {
			LoanApplicationMaster loanApplicationMaster1 = loanApplicationRepository.findOne(applicationId);
			if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster1)){
				if(loanApplicationMaster1.getBusinessTypeId().equals(CommonUtils.BusinessType.EXISTING_BUSINESS)){
					userMainType = CommonUtils.UserMainType.CORPORATE;
					PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository.findOne(applicationId);
					if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail)){
						if(primaryCorporateDetail.getPurposeOfLoanId()==1){
							productId = CommonUtils.LoanType.TERM_LOAN.getValue();
						}else if(primaryCorporateDetail.getPurposeOfLoanId()==2){
							productId = CommonUtils.LoanType.WORKING_CAPITAL.getValue();
						}
					}
				}
			}
		}
		DashboardProfileResponse dashboardProfileResponse = null;
		if (userMainType == CommonUtils.UserMainType.CORPORATE) {
			dashboardProfileResponse = new DashboardProfileResponse();
			dashboardProfileResponse.setProductId(productId);
			CorporateApplicantDetail corporateApplicantDetail = null;
			if(isSP){
				corporateApplicantDetail = corporateApplicantDetailRepository
						.getByApplicationAndUserIdForSP(userId, applicationId);
			}else{
				corporateApplicantDetail = corporateApplicantDetailRepository
						.getByApplicationAndUserId(userId, applicationId);
			}


			if (CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
				CommonDocumentUtils.endHook(logger, GET_BASIC_PROFILE_INFO);
				return dashboardProfileResponse;
			}
			dashboardProfileResponse.setPan(corporateApplicantDetail.getPanNo());
			dashboardProfileResponse.setId(corporateApplicantDetail.getId());
			dashboardProfileResponse.setApplicationId(corporateApplicantDetail.getApplicationId().getId());
			// Setting City Value
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId())) {
				dashboardProfileResponse.setCity(
						CommonDocumentUtils.getCity(corporateApplicantDetail.getRegisteredCityId(), oneFormClient));
			}

			// Setting State Value
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId())) {
				dashboardProfileResponse.setState(CommonDocumentUtils
						.getState(corporateApplicantDetail.getRegisteredStateId().longValue(), oneFormClient));
			}

			// Country State Value
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId())) {
				dashboardProfileResponse.setCountry(CommonDocumentUtils
						.getCountry(corporateApplicantDetail.getRegisteredCountryId().longValue(), oneFormClient));
			}

			dashboardProfileResponse
					.setName(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getOrganisationName())
							? corporateApplicantDetail.getOrganisationName() : "NA");
			dashboardProfileResponse.setAbout(corporateApplicantDetail.getAboutUs());

		} else {
			dashboardProfileResponse = new DashboardProfileResponse();
			dashboardProfileResponse.setProductId(productId);
			RetailApplicantDetail retailApplicantDetail = null;
			if(isSP){
				retailApplicantDetail = retailApplicantDetailRepository
						.getByApplicationAndUserIdForSP(userId, applicationId);
			}else{
				retailApplicantDetail = retailApplicantDetailRepository
						.getByApplicationAndUserId(userId, applicationId);
			}

			if (CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
				CommonDocumentUtils.endHook(logger, GET_BASIC_PROFILE_INFO);
				return dashboardProfileResponse;
			}
			dashboardProfileResponse.setPan(retailApplicantDetail.getPan());
			dashboardProfileResponse.setId(retailApplicantDetail.getId());
			dashboardProfileResponse.setApplicationId(retailApplicantDetail.getApplicationId().getId());
			// Setting City Value
			if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getAddressCity())) {
				dashboardProfileResponse
						.setCity(CommonDocumentUtils.getCity(retailApplicantDetail.getAddressCity(), oneFormClient));
			}

			// Setting State Value
			if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getAddressState())) {
				dashboardProfileResponse.setState(CommonDocumentUtils
						.getState(retailApplicantDetail.getAddressState().longValue(), oneFormClient));
			}

			// Country State Value
			if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getAddressCountry())) {
				dashboardProfileResponse.setCountry(CommonDocumentUtils
						.getCountry(retailApplicantDetail.getAddressCountry().longValue(), oneFormClient));
			}

			String name = !CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName())
					? retailApplicantDetail.getFirstName() + " " : "NA ";
			name = name.concat(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName())
					? retailApplicantDetail.getLastName() : "NA");
			dashboardProfileResponse.setName(name);
		}
		dashboardProfileResponse.setAddress();
		CommonDocumentUtils.endHook(logger, GET_BASIC_PROFILE_INFO);
		return dashboardProfileResponse;
	}

	@Override
	public Integer getCount(int userType) throws LoansException {
		CommonDocumentUtils.startHook(logger, "getCount");
		try {
			UserResponse response = usersClient.getActiveUserCount(userType);
			if (response != null) {
				return (Integer) response.getData();
			}
			CommonDocumentUtils.endHook(logger, "getCount");
			return 0;
		} catch (Exception e) {
			logger.error("Error while getting count for Dashbord : ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public UserResponse getFPBasicProfileInfo(Long userId) throws LoansException {
		CommonDocumentUtils.startHook(logger, "getFPBasicProfileInfo");
		try {
			CommonDocumentUtils.endHook(logger, "getFPBasicProfileInfo");
			return usersClient.getFPDashboardDetails(userId);
		} catch (Exception e) {
			logger.error("Error while getting FP Details on Dashbord : ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

}
